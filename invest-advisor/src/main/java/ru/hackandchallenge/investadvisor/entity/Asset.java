package ru.hackandchallenge.investadvisor.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "asset")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asset extends BaseEntity {

    @NotNull
    private String code;
    @NotNull
    private String fullName;
    @PositiveOrZero
    private BigDecimal cost;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetType type;
    @FutureOrPresent
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PortfolioAsset> portfolioAssets = new HashSet<>();


    public enum AssetType {
        STOCKS("stocks"), //акция
        CURRENCY("currency"), //валюта
        PREC_METALS("prec_metals"), //ценные металлы
        BONDS("bonds"); //облигации

        private final String type;

        AssetType(String type) {
            this.type = type;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(type);
        }

        /**
         * Получить значение енума из строки
         *
         * @param text строковое представление значения енума (напр. "stocks")
         *
         * @return значение енума (AssetType.STOCKS)
         */
        @JsonCreator
        public static AssetType fromValue(String text) {
            return Arrays.stream(AssetType.values())
                    .filter(candidate -> candidate.type.equals(text))
                    .findFirst()
                    .orElse(null);
        }
    }
}
