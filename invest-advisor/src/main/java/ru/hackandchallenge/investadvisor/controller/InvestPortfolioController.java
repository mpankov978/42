package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.dto.InvestPortfolioDto;
import ru.hackandchallenge.investadvisor.services.InvestPortfolioService;

@RestController
@AllArgsConstructor
@RequestMapping("/info/portfolio")
@Tag(name = "Контроллер инвест. портфеля", description = "API для операций над инвест. портфелем")
public class InvestPortfolioController {

    private final InvestPortfolioService investPortfolioService;

    @GetMapping
    @Operation(description = "Получить данные об инвест. портфеле")
    public InvestPortfolioDto getInfo(@RequestAttribute("clientId") Long clientId) {
        return investPortfolioService.getInfo(clientId);
    }


}
