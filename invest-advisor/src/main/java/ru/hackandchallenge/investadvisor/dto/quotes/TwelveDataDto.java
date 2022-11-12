package ru.hackandchallenge.investadvisor.dto.quotes;

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
public class TwelveDataDto {

    @JsonProperty("meta")
    private TwelveDataMetaDto meta;
    @JsonProperty("values")
    private List<TwelveDataValueDto> values;
    @JsonProperty("status")
    private String status;

}