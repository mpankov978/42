package ru.hackandchallenge.investadvisor.dto.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Данные котировок")
public class TwelveDataDto {

    @JsonProperty("meta")
    @Schema(description = "Метаданные")
    private TwelveDataMetaDto meta;

    @JsonProperty("values")
    @Schema(description = "Котировки")
    private List<TwelveDataValueDto> values;

    @JsonProperty("status")
    @Schema(description = "Статус")
    private String status;

}