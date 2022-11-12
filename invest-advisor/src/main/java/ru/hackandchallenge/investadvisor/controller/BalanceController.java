package ru.hackandchallenge.investadvisor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.services.InvestPortfolioService;

@RestController
@AllArgsConstructor
@RequestMapping("/operations/balance")
public class BalanceController {

    private final InvestPortfolioService investPortfolioService;

    @GetMapping("/generate-qr")
    public String generateQr() {
        return "https://qr.nspk.ru/AD100004BAL7227F9BNP6KNE007J9B3K?type=02&bank=100000000007&sum=5000&cur=RUB&crc=AB75";
    }

    @PostMapping("/enroll")
    public void enrollBalance(@RequestAttribute("clientId") Long clientId, @RequestParam String amount) {
        investPortfolioService.enrollBalance(clientId, amount);
    }


}
