package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.dto.operations.OperationHistoryDto;
import ru.hackandchallenge.investadvisor.entity.Asset;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;
import ru.hackandchallenge.investadvisor.entity.OperationHistory.OperationType;
import ru.hackandchallenge.investadvisor.repository.OperationHistoryRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OperationHistoryService {

    private OperationHistoryRepository repository;

    public void logAssetOperation(Long clientId, OperationType type, Asset asset, Integer amount) {
        var operationHistory = new OperationHistory();
        operationHistory.setClientId(clientId);
        operationHistory.setOperationType(type);
        operationHistory.setAsset(asset);
        operationHistory.setCost(asset.getCost());
        operationHistory.setAssetAmount(amount);
        operationHistory.setOperationTime(LocalDateTime.now());

        repository.save(operationHistory);
    }

    public void logEnrollOperation(Long clientId, BigDecimal cost) {
        var operationHistory = new OperationHistory();
        operationHistory.setClientId(clientId);
        operationHistory.setOperationType(OperationType.ENROLL);
        operationHistory.setCost(cost);
        operationHistory.setOperationTime(LocalDateTime.now());

        repository.save(operationHistory);
    }

    public List<OperationHistoryDto> getHistory(Long clientId, Integer days, boolean all) {
        List<OperationHistory> history;
        if (all) {
            history = repository.findOperationHistoriesByClientId(clientId);
        } else if (days == 0) {
            return Collections.emptyList();
        } else {
            history = repository.findOperationHistoriesByClientIdAndBetweenDays(clientId, days);
        }
        return history.stream()
                .map(operation -> new OperationHistoryDto(operation.getId(), operation.getClientId(),
                        operation.getOperationType(), operation.getAsset() != null ? operation.getAsset().getCode() : null,
                        operation.getAsset() != null ? operation.getAsset().getFullName() : null, operation.getCost(),
                        operation.getAssetAmount(), operation.getOperationTime()))
                .toList();
    }
}
