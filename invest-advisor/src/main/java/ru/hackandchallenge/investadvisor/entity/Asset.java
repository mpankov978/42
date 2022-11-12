package ru.hackandchallenge.investadvisor.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
    private BigDecimal cost;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetType type;
    private LocalDateTime lastUpdated;

    @ManyToMany
    @JoinTable(name = "invest_portfolio_assets",
            joinColumns = @JoinColumn(name = "assets_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "invest_portfolio_id", referencedColumnName = "id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<InvestPortfolio> investPortfolios = new java.util.ArrayList<>();


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
