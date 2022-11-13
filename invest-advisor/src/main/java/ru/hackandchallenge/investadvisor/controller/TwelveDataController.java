package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hackandchallenge.investadvisor.collectors.TwelveDataCollector;
import ru.hackandchallenge.investadvisor.dto.quotes.TwelveDataDto;

import java.util.Collection;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/twelvedata")
@Tag(name = "Контроллер котировок", description = "API для получения данных котировок с сайта Twelvedata.com")
public class TwelveDataController {

    private final TwelveDataCollector twelvedataCollector;

    @GetMapping("/get")
    @Operation(description = "Получить данные котировок")
    public Set<TwelveDataDto> getItems(@RequestParam Collection<String> items, @RequestParam String interval) {
        return twelvedataCollector.getItems(items, interval);
    }

}
