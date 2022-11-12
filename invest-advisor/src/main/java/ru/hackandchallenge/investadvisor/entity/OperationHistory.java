package ru.hackandchallenge.investadvisor.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "operation_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationHistory extends BaseEntity {

    @NotNull
    private Long clientId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @PositiveOrZero
    private BigDecimal assetCost;

    @PositiveOrZero
    private Integer assetAmount;

    @PastOrPresent
    private LocalDateTime operationTime;

    public enum OperationType {
        BUY("buy"),
        SELL("sell");

        private final String type;

        OperationType(String type) {
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
         * @param text строковое представление значения енума (напр. "buy")
         *
         * @return значение енума (OperationType.BUY)
         */
        @JsonCreator
        public static OperationType fromValue(String text) {
            return Arrays.stream(OperationType.values())
                    .filter(candidate -> candidate.type.equals(text))
                    .findFirst()
                    .orElse(null);
        }
    }

}
