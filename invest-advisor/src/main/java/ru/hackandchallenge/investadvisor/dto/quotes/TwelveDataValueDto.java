package ru.hackandchallenge.investadvisor.dto.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запись истории котировки")
public class TwelveDataValueDto {

    @JsonProperty("datetime")
    @Schema(description = "Время, на которую актуальна котировки")
    private String datetime;

    @JsonProperty("open")
    @Schema(description = "Цена открытия")
    private BigDecimal open;
    @JsonProperty("high")
    @Schema(description = "Высшая цена")
    private BigDecimal high;
    @JsonProperty("low")
    @Schema(description = "Низшая цена")
    private BigDecimal low;
    @JsonProperty("close")
    @Schema(description = "Цена закрытия")
    private BigDecimal close;
    @JsonProperty("volume")
    @Schema(description = "Объем торговли активом")
    private String volume;


}
