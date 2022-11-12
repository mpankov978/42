package ru.hackandchallenge.investadvisor.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Контейнер для токена аутентификации")
public record CheckAuthRequest(@Schema(required = true, description = "Токен аутентификации, полученный от API брокера") String jwt) {

}
