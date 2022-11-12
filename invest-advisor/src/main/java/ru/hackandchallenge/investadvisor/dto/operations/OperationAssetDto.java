package ru.hackandchallenge.investadvisor.dto.operations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные об операции над активом")
public class OperationAssetDto {

    @Schema(description = "Код актива")
    private String code;

    @Schema(description = "Количество")
    private Integer amount;
}
