package ru.hackandchallenge.investadvisor.dto.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Метаданные котировки")
public class TwelveDataMetaDto {

    @JsonProperty("symbol")
    @Schema(description = "Код актива")
    private String symbol;

    @JsonProperty("interval")
    @Schema(description = "Промежутки между данными котировок. Доступные значения: 1min, 5min, 15min, 30min, 45min, 1h, 2h, 4h, 8h, 1day, 1week, 1month")
    private String interval;

    @JsonProperty("currency")
    @Schema(description = "Валюта котировки")
    private String currency;

    @JsonProperty("exchange_timezone")
    @Schema(description = "Таймзона торговли активом")
    private String exchangeTimezone;

    @JsonProperty("exchange")
    @Schema(description = "Место торговли активом")
    private String exchange;

    @JsonProperty("mic_code")
    private String micCode;

    @JsonProperty("type")
    @Schema(description = "Тип рынка на котором торгуется актив")
    private String type;

}
