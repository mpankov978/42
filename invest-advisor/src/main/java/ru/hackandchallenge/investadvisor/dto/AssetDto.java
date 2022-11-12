package ru.hackandchallenge.investadvisor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hackandchallenge.investadvisor.entity.Asset;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Asset} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные об активе")
public class AssetDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Код")
    private String code;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Стоимость")
    private BigDecimal cost;
    @Schema(description = "Количество (у инвест. профиля)")
    private Integer amount;
    @Schema(description = "На какое время актуальны данные")
    private LocalDateTime lastUpdated;

}