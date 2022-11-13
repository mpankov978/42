package ru.hackandchallenge.investadvisor.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity {

    @NotNull
    private Long clientId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private Long assetId;
    private BigDecimal oldCost;
    private BigDecimal actualCost;

    @NotNull
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "invest_portfolio_id")
    private InvestPortfolio investPortfolio;


    public enum NotificationType {
        ASSET_COST_DROP("asset-cost-drop"); //критическое изменение цены активов в портфеле

        private final String type;

        NotificationType(String type) {
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
         * @param text строковое представление значения енума (напр. "asset-cost-drop")
         *
         * @return значение енума (NotificationType.ASSET_COST_DROP)
         */
        @JsonCreator
        public static NotificationType fromValue(String text) {
            return Arrays.stream(NotificationType.values())
                    .filter(candidate -> candidate.type.equals(text))
                    .findFirst()
                    .orElse(null);
        }
    }
}
