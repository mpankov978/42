package ru.hackandchallenge.investadvisor.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Schema(description = "Контейнер для токена аутентификации")
@Validated
public record CheckAuthRequest(@NotBlank @Schema(required = true, description = "Токен аутентификации, полученный от API брокера") String jwt) {

}
