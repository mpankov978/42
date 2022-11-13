package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.aspects.ClientOnly;
import ru.hackandchallenge.investadvisor.aspects.OperatorOnly;
import ru.hackandchallenge.investadvisor.collectors.QuoteRbcCollector;
import ru.hackandchallenge.investadvisor.dto.NewsDto;
import ru.hackandchallenge.investadvisor.services.InvestingNewsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/news")
@Tag(name = "Контроллер новостей", description = "API для получения новостей")
public class NewsController {

    public static final Map<String, String> ITEMS_MAP = new HashMap<>();
    static {
        ITEMS_MAP.put("aapl", "apple-computer-inc");
        ITEMS_MAP.put("ibm", "ibm");
        ITEMS_MAP.put("ozon", "ozon-holdings-plc");
        ITEMS_MAP.put("yndx", "yandex");
        ITEMS_MAP.put("hhr", "headhunter-group-plc");
    }

    private final InvestingNewsService newsService;
    private final QuoteRbcCollector collector;

    @GetMapping("/investing/{item}")
    @Operation(description = "Получить новости с Investing.com")
    public List<NewsDto> getNews(
            @PathVariable String item,
            @RequestParam Integer limit) {
        return newsService.getNewsByItem(item, limit);
    }

    @ClientOnly
    @GetMapping("/investing/mine")
    @Operation(description = "Получить новости об активах текущего клиента с Investing.com")
    public List<NewsDto> getMyNews(
            @RequestAttribute Long clientId) {
        return newsService.getClientNews(clientId);
    }

    @OperatorOnly
    @GetMapping("investing/client/{clientId}")
    @Operation(description = "Получить новости об активах для указанного клиента с Investing.com")
    public List<NewsDto> getClientNews(
            @PathVariable Long clientId) {
        return newsService.getClientNews(clientId);
    }

    @GetMapping("/dividends")
    @Operation(description = "Получить новости с РБК.Дивиденды")
    public List<NewsDto> dividends(@RequestParam Integer limit) {
        return collector.collect(limit);
    }
}
