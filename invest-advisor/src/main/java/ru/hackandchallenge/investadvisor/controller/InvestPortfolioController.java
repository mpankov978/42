package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.aspects.ClientOnly;
import ru.hackandchallenge.investadvisor.aspects.OperatorOnly;
import ru.hackandchallenge.investadvisor.dto.InvestPortfolioDto;
import ru.hackandchallenge.investadvisor.services.InvestPortfolioService;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/info/portfolio")
@Tag(name = "Контроллер инвест. портфеля", description = "API для операций над инвест. портфелем")
public class InvestPortfolioController {

    private final InvestPortfolioService investPortfolioService;

    @ClientOnly
    @GetMapping
    @Operation(description = "Получить данные об инвест. портфеле для текущего пользователя")
    public InvestPortfolioDto getInfo(@RequestAttribute("clientId") Long clientId) {
        return investPortfolioService.getInfo(clientId, true);
    }

    @OperatorOnly
    @GetMapping("/{clientId}")
    @Operation(description = "Получить данные об инвест. портфеле для указанного пользователя")
    public InvestPortfolioDto getInfoForClient(@PathVariable("clientId") Long clientId) {
        return investPortfolioService.getInfo(clientId, true);
    }

    @ClientOnly
    @GetMapping("/totalInvestments")
    @Operation(description = "Получить общую вложенную сумму в портфель за промежуток времени")
    public BigDecimal getTotalInvestments(@RequestAttribute("clientId") Long clientId, @RequestParam(required = false) Integer days, @RequestParam(required = false) boolean all) {
        return investPortfolioService.getTotalInvestments(clientId, days, all);
    }

    @ClientOnly
    @PostMapping("/monitor")
    @Operation(description = "Получить общую вложенную сумму в портфель за промежуток времени")
    public void setMonitorStatus(@RequestAttribute("clientId") Long clientId, @RequestParam boolean enabled) {
        investPortfolioService.setMonitorStatus(clientId, enabled);
    }


}
