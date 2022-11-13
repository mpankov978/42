package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.aspects.ClientOnly;
import ru.hackandchallenge.investadvisor.aspects.OperatorOnly;
import ru.hackandchallenge.investadvisor.dto.operations.OperationHistoryDto;
import ru.hackandchallenge.investadvisor.services.OperationHistoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/operations/history")
@Tag(name = "Контроллер истории операций", description = "API для просмотра истории операций инвест. портфеля")
public class OperationHistoryController {

    private final OperationHistoryService operationHistoryService;

    @ClientOnly
    @GetMapping
    @Operation(description = "Получить историю операций за промежуток времени (дней) для текущего пользователя")
    public List<OperationHistoryDto> getHistory(@RequestAttribute("clientId") Long clientId, @RequestParam(required = false) Integer days, @RequestParam(required = false) boolean all) {
        return operationHistoryService.getHistory(clientId, days, all);
    }

    @OperatorOnly
    @GetMapping("/{clientId}")
    @Operation(description = "Получить историю операций за промежуток времени (дней) для указанного пользователя")
    public List<OperationHistoryDto> getHistoryForClient(@PathVariable("clientId") Long clientId, @RequestParam(required = false) Integer days, @RequestParam(required = false) boolean all) {
        return operationHistoryService.getHistory(clientId, days, all);
    }

}
