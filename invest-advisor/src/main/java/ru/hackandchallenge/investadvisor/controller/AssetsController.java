package ru.hackandchallenge.investadvisor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.hackandchallenge.investadvisor.dto.operations.OperationAssetDto;
import ru.hackandchallenge.investadvisor.services.AssetsService;

@RestController
@AllArgsConstructor
@RequestMapping("/operations/assets")
public class AssetsController extends ResponseEntityExceptionHandler {

    private final AssetsService assetsService;

    @PostMapping("/buy")
    public void buyAsset(@RequestAttribute("clientId") Long clientId, @RequestBody OperationAssetDto operationAssetDto) {
        assetsService.processBuyOperation(clientId, operationAssetDto);
    }

    @PostMapping("/sell")
    public void sellAsset(@RequestAttribute("clientId") Long clientId, @RequestBody OperationAssetDto operationAssetDto) {
        assetsService.processSellOperation(clientId, operationAssetDto);
    }
}
