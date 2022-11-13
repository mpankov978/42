package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hackandchallenge.investadvisor.collectors.QuoteRbcCollector;
import ru.hackandchallenge.investadvisor.dto.NewsDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/dividends")
@Tag(name = "Контроллер новостей с https://quote.rbc.ru/tag/Dividends", description = "API для получения новостей с https://quote.rbc.ru/tag/Dividends")
public class QuoteRbcController {

    private final QuoteRbcCollector collector;

    @GetMapping
    @Operation(description = "Получить новости с https://quote.rbc.ru/tag/Dividends")
    public List<NewsDto> dividends(@RequestParam Integer limit) {
        return collector.collect(limit);
    }

}
