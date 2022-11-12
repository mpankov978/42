package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.dto.AssetDto;
import ru.hackandchallenge.investadvisor.dto.AssetExtendedDto;
import ru.hackandchallenge.investadvisor.dto.operations.OperationAssetDto;
import ru.hackandchallenge.investadvisor.services.AssetsService;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Контроллер активов", description = "API для операций с активами")
public class AssetsController {

    private final AssetsService assetsService;

    @GetMapping("/info/assets")
    @Operation(description = "Получить список всех активов")
    public List<AssetDto> getAssets() {
        return assetsService.getAssets();
    }

    @GetMapping("/info/assets/{code}")
    @Operation(description = "Получить актив по коду")
    public AssetExtendedDto getAssetByCode(@PathVariable String code) {
        return assetsService.getAssetByCode(code);
    }

    @PostMapping("/operations/assets/buy")
    @Operation(description = "Купить актив")
    public void buyAsset(@RequestAttribute("clientId") Long clientId, @RequestBody OperationAssetDto operationAssetDto) {
        assetsService.processBuyOperation(clientId, operationAssetDto);
    }

    @PostMapping("/operations/assets/sell")
    @Operation(description = "Продать актив")
    public void sellAsset(@RequestAttribute("clientId") Long clientId, @RequestBody OperationAssetDto operationAssetDto) {
        assetsService.processSellOperation(clientId, operationAssetDto);
    }
}
