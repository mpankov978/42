package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.aspects.ClientOnly;
import ru.hackandchallenge.investadvisor.services.InvestPortfolioService;

@RestController
@AllArgsConstructor
@RequestMapping("/operations/balance")
@Tag(name = "Контроллер баланса", description = "API для операций с балансом инвест. портфеля")
public class BalanceController {

    private final InvestPortfolioService investPortfolioService;

    @GetMapping("/generate-qr")
    @Operation(description = "Сгенерировать QR-код для оплаты")
    public String generateQr() {
        return "https://qr.nspk.ru/AD100004BAL7227F9BNP6KNE007J9B3K?type=02&bank=100000000007&sum=5000&cur=RUB&crc=AB75";
    }

    @ClientOnly
    @PostMapping("/enroll")
    @Operation(description = "Пополнить баланс")
    public void enrollBalance(@RequestAttribute("clientId") Long clientId, @RequestParam String amount) {
        investPortfolioService.enrollBalance(clientId, amount);
    }


}
