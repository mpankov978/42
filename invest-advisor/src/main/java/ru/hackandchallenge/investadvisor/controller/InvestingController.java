package ru.hackandchallenge.investadvisor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.collectors.investing.InvestingNewsCollector;
import ru.hackandchallenge.investadvisor.dto.investing.InvestingNewsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/investing")
public class InvestingController {

    private static final HashMap<String, String> ITEMS_MAP = new HashMap<>();
    static {
        ITEMS_MAP.put("ibm", "ibm");
        ITEMS_MAP.put("aapl", "apple-computer-inc");
        ITEMS_MAP.put("hhr", "headhunter-group-plc");
        ITEMS_MAP.put("hydr", "gidroogk-011d");
        ITEMS_MAP.put("ozon", "ozon-holdings-plc");
    }

    private final InvestingNewsCollector newsCollector;

    @GetMapping("/news/{type}/{item}")
    public List<InvestingNewsDto> getNews(
            @PathVariable String type,
            @PathVariable String item,
            @RequestParam Integer limit) {
        String itemInInvesting = ITEMS_MAP.get(item.toLowerCase(Locale.ROOT)) != null
                ? ITEMS_MAP.get(item.toLowerCase(Locale.ROOT))
                : item;
        return newsCollector.collect(type, itemInInvesting, limit);
    }
}
