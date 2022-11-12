package ru.hackandchallenge.investadvisor.dto;

import lombok.Data;
import ru.hackandchallenge.investadvisor.entity.Asset;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Asset} entity
 */
@Data
public final class AssetDto {
    private Long id;
    private String code;
    private String fullName;
    private BigDecimal cost;
    private LocalDateTime lastUpdated;
}