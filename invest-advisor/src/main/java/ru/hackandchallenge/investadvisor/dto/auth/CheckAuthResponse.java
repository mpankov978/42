package ru.hackandchallenge.investadvisor.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(description = "Данные ответа при аутентификации пользователя в системе брокера")
public class CheckAuthResponse {

    @Schema(description = "Идентификатор пользователя")
    private Integer userId;

    @Schema(description = "Роль пользователя")
    private String role;

}
