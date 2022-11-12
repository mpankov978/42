package ru.hackandchallenge.investadvisor.dto.investing;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Данные новости")
public class InvestingNewsDto {

    @Schema(description = "Заголовок")
    private String title;

    @Schema(description = "Ссылка на страницу")
    private String link;

    @Schema(description = "Краткое описание")
    private String preview;

    @Schema(description = "Источник и время новости")
    private String from;

}
