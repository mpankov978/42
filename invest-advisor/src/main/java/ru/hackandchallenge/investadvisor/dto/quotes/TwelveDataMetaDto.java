package ru.hackandchallenge.investadvisor.dto.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TwelveDataMetaDto {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("interval")
    private String interval;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("exchange_timezone")
    private String exchangeTimezone;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("mic_code")
    private String micCode;

    @JsonProperty("type")
    private String type;

}
