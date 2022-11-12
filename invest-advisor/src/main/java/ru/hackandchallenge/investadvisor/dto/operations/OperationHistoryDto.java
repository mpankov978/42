package ru.hackandchallenge.investadvisor.dto.operations;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link ru.hackandchallenge.investadvisor.entity.OperationHistory} entity
 */
public record OperationHistoryDto(@Schema(description = "Идентификатор") Long id,
                                  @Schema(description = "Идентификатор клиента") @NotNull Long clientId,
                                  @Schema(description = "Тип операции (BUY, SELL)") @NotNull OperationHistory.OperationType operationType,
                                  @Schema(description = "Код") @NotNull String code,
                                  @Schema(description = "Полное название") @NotNull String fullName,
                                  @Schema(description = "Стоимость на момент проведения операции") @PositiveOrZero BigDecimal assetCost,
                                  @Schema(description = "Количество") @PositiveOrZero Integer assetAmount,
                                  @Schema(description = "Время операции") @PastOrPresent LocalDateTime operationTime) implements Serializable {
}