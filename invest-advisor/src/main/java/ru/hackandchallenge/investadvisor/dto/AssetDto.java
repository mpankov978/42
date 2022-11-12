package ru.hackandchallenge.investadvisor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hackandchallenge.investadvisor.entity.Asset;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Asset} entity
 */
@Schema(description = "Данные об активе")
public record AssetDto(@Schema(description = "Идентификатор") Long id,
                       @Schema(description = "Код") String code,
                       @Schema(description = "Полное название") String fullName,
                       @Schema(description = "Стоимость") BigDecimal cost,
                       @Schema(description = "Количество (у инвест. профиля)") Integer amount,
                       @Schema(description = "На какое время актуальны данные") LocalDateTime lastUpdated) {
}