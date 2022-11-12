package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.dto.operations.OperationAssetDto;
import ru.hackandchallenge.investadvisor.services.AssetsService;

@RestController
@AllArgsConstructor
@RequestMapping("/operations/assets")
@Tag(name = "Контроллер активов", description = "API для операций с активами")
public class AssetsController {

    private final AssetsService assetsService;

    @PostMapping("/buy")
    @Operation(description = "Купить актив")
    public void buyAsset(@RequestAttribute("clientId") Long clientId, @RequestBody OperationAssetDto operationAssetDto) {
        assetsService.processBuyOperation(clientId, operationAssetDto);
    }

    @PostMapping("/sell")
    @Operation(description = "Продать актив")
    public void sellAsset(@RequestAttribute("clientId") Long clientId, @RequestBody OperationAssetDto operationAssetDto) {
        assetsService.processSellOperation(clientId, operationAssetDto);
    }
}
