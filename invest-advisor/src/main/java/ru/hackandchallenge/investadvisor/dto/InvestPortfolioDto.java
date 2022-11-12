package ru.hackandchallenge.investadvisor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A DTO for the {@link InvestPortfolio} entity
 */
@Schema(description = "Данные об инвест. портфеле")
public record InvestPortfolioDto(@Schema(description = "Идентификатор") Long id,
                                 @Schema(description = "Идентификатор клиента") Long clientId,
                                 @Schema(description = "Список активов") List<AssetDto> assets,
                                 @Schema(description = "Баланс счета") BigDecimal balance,
                                 @Schema(description = "Сумма всех активов") BigDecimal assetSum,
                                 @Schema(description = "Полный баланс портфеля") BigDecimal totalSum) implements Serializable {
}