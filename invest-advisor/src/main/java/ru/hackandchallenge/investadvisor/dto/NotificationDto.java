package ru.hackandchallenge.investadvisor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hackandchallenge.investadvisor.entity.Notification;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Notification} entity
 */
@Schema(description = "Уведомление")
public record NotificationDto(@Schema(description = "Идентификатор") Long id,
                              @NotNull @Schema(description = "Идентификатор клиента") Long clientId,
                              @NotNull @Schema(description = "Тип уведомления") Notification.NotificationType type,
                              @Schema(description = "Полное название актива") String fullName,
                              @Schema(description = "Стоимость актива на момент покупки") BigDecimal oldCost,
                              @Schema(description = "Текущая стоимость актива") BigDecimal actualCost,
                              @Schema(description = "Дата уведомления") @NotNull LocalDateTime time) implements Serializable {
}