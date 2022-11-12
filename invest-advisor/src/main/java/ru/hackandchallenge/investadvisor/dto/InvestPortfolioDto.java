package ru.hackandchallenge.investadvisor.dto;

import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A DTO for the {@link InvestPortfolio} entity
 */
public record InvestPortfolioDto(Long id, Long clientId, List<AssetDto> assets,
                                 BigDecimal balance, BigDecimal assetSum, BigDecimal totalSum) implements Serializable {
}