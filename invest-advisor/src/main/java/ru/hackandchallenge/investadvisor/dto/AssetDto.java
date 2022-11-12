package ru.hackandchallenge.investadvisor.dto;

import ru.hackandchallenge.investadvisor.entity.Asset;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Asset} entity
 */
public record AssetDto(Long id, String code, String fullName, BigDecimal cost, Integer amount,
                       LocalDateTime lastUpdated) {
}