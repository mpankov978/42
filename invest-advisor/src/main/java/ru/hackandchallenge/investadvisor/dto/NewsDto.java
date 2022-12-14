package ru.hackandchallenge.investadvisor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Данные новости")
public class NewsDto {

    @Schema(description = "Заголовок")
    private String title;

    @Schema(description = "Ссылка на страницу")
    private String link;

    @Schema(description = "Краткое описание")
    private String preview;

    @Schema(description = "Источник и время новости")
    private String from;

}
