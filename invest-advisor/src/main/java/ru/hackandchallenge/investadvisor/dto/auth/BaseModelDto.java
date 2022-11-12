package ru.hackandchallenge.investadvisor.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Контейнер для токена аутентификации")
public class BaseModelDto {

    @Schema(description = "Токен аутентификации, полученный от API брокера")
    private String jwtToken;

}
