package ru.hackandchallenge.investadvisor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.collectors.quotes.TwelveDataCollector;
import ru.hackandchallenge.investadvisor.dto.quotes.TwelveDataDto;

import java.util.Collection;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/twelvedata")
public class TwelveDataController {

    private final TwelveDataCollector twelvedataCollector;

    @GetMapping("/get")
    public Set<TwelveDataDto> getItems(@RequestParam Collection<String> items, @RequestParam String interval) {
        return twelvedataCollector.getItems(items, interval);
    }

}
