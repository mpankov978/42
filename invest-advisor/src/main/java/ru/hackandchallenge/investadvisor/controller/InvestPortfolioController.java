package ru.hackandchallenge.investadvisor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.dto.InvestPortfolioDto;
import ru.hackandchallenge.investadvisor.services.InvestPortfolioService;

@RestController
@AllArgsConstructor
@RequestMapping("/info/portfolio")
public class InvestPortfolioController {

    private final InvestPortfolioService investPortfolioService;

    @GetMapping
    public InvestPortfolioDto getInfo(@RequestAttribute("clientId") Long clientId) {
        return investPortfolioService.getInfo(clientId);
    }


}
