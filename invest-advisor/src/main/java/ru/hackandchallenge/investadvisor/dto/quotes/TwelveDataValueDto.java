package ru.hackandchallenge.investadvisor.dto.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запись истории котировки")
public class TwelveDataValueDto {

    @JsonProperty("datetime")
    @Schema(description = "Время, на которую актуальна котировки")
    private String datetime;

    @JsonProperty("open")
    @Schema(description = "Цена открытия")
    private String open;

    @JsonProperty("high")
    @Schema(description = "Высшая цена")
    private String high;

    @JsonProperty("low")
    @Schema(description = "Низшая цена")
    private String low;

    @JsonProperty("close")
    @Schema(description = "Цена закрытия")
    private String close;

    @JsonProperty("volume")
    @Schema(description = "Объем торговли активом")
    private String volume;


}
