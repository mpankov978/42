package ru.hackandchallenge.investadvisor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.collectors.investing.InvestingNewsCollector;
import ru.hackandchallenge.investadvisor.collectors.investing.InvestingNewsDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/investing")
public class InvestingController {

    private final InvestingNewsCollector newsCollector;

    @GetMapping("/news/{type}/{item}")
    public List<InvestingNewsDto> getNews(
            @PathVariable String type,
            @PathVariable String item,
            @RequestParam Integer limit) {
        return newsCollector.collect(type, item, limit);
    }
}
