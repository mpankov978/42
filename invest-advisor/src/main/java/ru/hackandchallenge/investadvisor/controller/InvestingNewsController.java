package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.dto.investing.InvestingNewsDto;
import ru.hackandchallenge.investadvisor.services.InvestingNewsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/collectors/investing")
@Tag(name = "Контроллер новостей", description = "API для получения новостей с Investing.com")
public class InvestingNewsController {

    public static final Map<String, String> ITEMS_MAP = new HashMap<>();
    static {
        ITEMS_MAP.put("ibm", "ibm");
        ITEMS_MAP.put("aapl", "apple-computer-inc");
        ITEMS_MAP.put("hhr", "headhunter-group-plc");
        ITEMS_MAP.put("hydr", "gidroogk-011d");
        ITEMS_MAP.put("ozon", "ozon-holdings-plc");
        ITEMS_MAP.put("tatn", "tatneft_rts");
        ITEMS_MAP.put("chmf", "severstal_rts");
        ITEMS_MAP.put("vkcoq", "mail.ru-grp-wi");
        ITEMS_MAP.put("mgnt", "magnit_rts");
        ITEMS_MAP.put("aflt", "aeroflot");
        ITEMS_MAP.put("afks", "afk-sistema_rts");
        ITEMS_MAP.put("rtkm", "rostelecom");
        ITEMS_MAP.put("nvtk", "novatek_rts");
        ITEMS_MAP.put("pikk", "pik_rts");
        ITEMS_MAP.put("nlmk", "nlmk_rts");
        ITEMS_MAP.put("dsky", "detskiy-mir-pao");
    }

    private final InvestingNewsService newsService;

    @GetMapping("/news/{item}")
    @Operation(description = "Получить новости с Investing.com")
    public List<InvestingNewsDto> getNews(
            @PathVariable String item,
            @RequestParam Integer limit) {
        return newsService.getNewsByItem(item, limit);
    }

    @GetMapping("/news/mine")
    @Operation(description = "Получить новости с Investing.com для авторизованного пользователя")
    public List<InvestingNewsDto> getMyNews(
            @RequestAttribute Long clientId) {
        return newsService.getClientNews(clientId);
    }

    @GetMapping("/client/{clientId}/news")
    @Operation(description = "Получить новости с Investing.com для указанного пользователя")
    public List<InvestingNewsDto> getClientNews(
            @PathVariable Long clientId) {
        return newsService.getClientNews(clientId);
    }
}
