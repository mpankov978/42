package ru.hackandchallenge.investadvisor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.hackandchallenge.investadvisor.dto.quotes.TwelveDataValueDto;

import java.util.List;

@Data
@Schema(description = "Данные об активе, с котировками")
public class AssetExtendedDto extends AssetDto {

    @Schema(description = "Котировки")
    private List<TwelveDataValueDto> quotes;

}
