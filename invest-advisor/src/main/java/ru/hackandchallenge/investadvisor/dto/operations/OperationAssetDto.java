package ru.hackandchallenge.investadvisor.dto.operations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Validated
@Schema(description = "Данные об операции над активом")
public class OperationAssetDto {

    @Schema(required = true, description = "Код актива")
    @NotBlank(message = "Введите код актива")
    private String code;

    @Schema(required = true, description = "Количество")
    @Positive(message = "Введите количество активов")
    private Integer amount;
}
