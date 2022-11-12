package ru.hackandchallenge.investadvisor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.collectors.twelvedata.TwelvedataCollector;
import ru.hackandchallenge.investadvisor.dto.TwelvedataDto;

import java.util.Collection;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/twelvedata")
public class TwelvedataController {

    private final TwelvedataCollector twelvedataCollector;

    @GetMapping("/get")
    public Set<TwelvedataDto> getItems(@RequestParam Collection<String> items, @RequestParam String interval) {
        return twelvedataCollector.getItems(items, interval);
    }

}
