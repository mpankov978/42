package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;
import ru.hackandchallenge.investadvisor.entity.Notification;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;
import ru.hackandchallenge.investadvisor.repository.InvestPortfoliosRepository;
import ru.hackandchallenge.investadvisor.repository.NotificationRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PortfolioMonitor {

    private final InvestPortfoliosRepository portfoliosRepository;
    private final AssetsService assetsService;
    private final OperationHistoryService operationHistoryService;
    private final NotificationRepository notificationRepository;

    @Value("${variables.portfolioMonitorTrigger}")
    private Integer portfolioMonitorTrigger;

    @Scheduled(cron = "${variables.portfolioMonitorCron}")
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
                if (BigDecimal.valueOf(100).subtract(v.divide(operation.getCost(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))).compareTo(BigDecimal.valueOf(portfolioMonitorTrigger)) <= 0) {
                    notificationRepository.save(new Notification(portfolio.getClientId(), Notification.NotificationType.ASSET_COST_DROP,
                            operation.getAsset().getId(), operation.getCost(), v, LocalDateTime.now(), portfolio));
                }
            });


        });
    }
}
