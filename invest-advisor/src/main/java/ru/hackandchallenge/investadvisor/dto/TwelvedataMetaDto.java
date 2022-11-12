package ru.hackandchallenge.investadvisor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwelvedataMetaDto {
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
