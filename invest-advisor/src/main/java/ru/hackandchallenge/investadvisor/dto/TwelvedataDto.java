package ru.hackandchallenge.investadvisor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TwelvedataDto {

    @JsonProperty("meta")
    private TwelvedataMetaDto meta;
    @JsonProperty("values")
    private List<TwelvedataValueDto> values;
    @JsonProperty("status")
    private String status;

}