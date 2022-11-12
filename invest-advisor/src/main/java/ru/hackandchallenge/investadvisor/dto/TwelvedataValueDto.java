package ru.hackandchallenge.investadvisor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwelvedataValueDto {

    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("open")
    private String open;
    @JsonProperty("high")
    private String high;
    @JsonProperty("low")
    private String low;
    @JsonProperty("close")
    private String close;
    @JsonProperty("volume")
    private String volume;

}
