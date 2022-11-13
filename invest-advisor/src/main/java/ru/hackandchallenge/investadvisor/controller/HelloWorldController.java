package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;
import ru.hackandchallenge.investadvisor.entity.Notification;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;
import ru.hackandchallenge.investadvisor.repository.InvestPortfoliosRepository;
import ru.hackandchallenge.investadvisor.repository.NotificationRepository;
import ru.hackandchallenge.investadvisor.services.AssetsService;
import ru.hackandchallenge.investadvisor.services.OperationHistoryService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Tag(name = "Тестовый контроллер", description = "API для проверки работоспособности приложения")
public class HelloWorldController {

    @GetMapping(path = "/hello")
    @Operation(description = "Получить идентификатор клиента из токена")
    @ResponseBody
    public Integer getPage(@RequestAttribute("clientId") Integer clientId) {
        return clientId;
    }

    private final InvestPortfoliosRepository portfoliosRepository;
    private final AssetsService assetsService;
    private final OperationHistoryService operationHistoryService;
    private final NotificationRepository notificationRepository;

    @GetMapping("/test")
    public void checkPortfolioAssets() {
        List<InvestPortfolio> portfoliosToCheck = portfoliosRepository.findInvestPortfoliosByNeedMonitorIsTrue();
        if (portfoliosToCheck.isEmpty()) {
            return;
        }
        assetsService.updateAllAssetsData();

        portfoliosToCheck.forEach(portfolio -> {
            List<OperationHistory> history = operationHistoryService.getHistory(portfolio.getClientId(), null, true);
            Map<String, BigDecimal> collect = portfolio.getPortfolioAssets()
                    .stream()
                    .collect(Collectors.toMap(v -> v.getAsset().getCode(), v -> v.getAsset().getCost()));
            collect.forEach((k, v) -> {
                OperationHistory operation = history.stream()
                        .filter(h -> OperationHistory.OperationType.BUY == h.getOperationType())
                        .filter(h -> k.equalsIgnoreCase(h.getAsset().getCode()))
                        .max(Comparator.comparing(OperationHistory::getOperationTime))
                        .orElseThrow(EntityNotFoundException::new);
                //100% - ((текущ.цена/стар.цена) * 100% ) <= 75%
                if (BigDecimal.valueOf(100).subtract(v.divide(operation.getCost(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))).compareTo(BigDecimal.valueOf(75)) <= 0) {
                    notificationRepository.save(new Notification(portfolio.getClientId(), Notification.NotificationType.ASSET_COST_DROP,
                            operation.getAsset().getId(), operation.getCost(), v, LocalDateTime.now(), portfolio));
                }
            });


        });
    }

}
