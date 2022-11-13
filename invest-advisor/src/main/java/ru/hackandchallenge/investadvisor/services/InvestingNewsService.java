package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.collectors.InvestingNewsCollector;
import ru.hackandchallenge.investadvisor.dto.InvestPortfolioDto;
import ru.hackandchallenge.investadvisor.dto.NewsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.hackandchallenge.investadvisor.controller.NewsController.ITEMS_MAP;

@Service
@AllArgsConstructor
public class InvestingNewsService {

    private static final Pattern ARTICLE_ID_PATTERN = Pattern.compile("\\d+$");

    private final InvestingNewsCollector collector;
    private final InvestPortfolioService portfolioService;

    public List<NewsDto> getClientNews(Long clientId) {
        InvestPortfolioDto dto = portfolioService.getInfo(clientId, false);
        List<NewsDto> news = dto.assets()
                .stream()
                .map(asset -> {
                    String value = ITEMS_MAP.get(asset.getCode());
                    return collector.collect(value, 3);
                })
                .reduce(new ArrayList<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
        news.sort((a, b) -> -compare(a, b));
        return news;
    }

    private int compare(NewsDto a, NewsDto b) {
        Matcher idMatcher;
        idMatcher = ARTICLE_ID_PATTERN.matcher(a.getLink());
        idMatcher.find();
        int aId = Integer.parseInt(idMatcher.group());
        idMatcher = ARTICLE_ID_PATTERN.matcher(b.getLink());
        idMatcher.find();
        int bId = Integer.parseInt(idMatcher.group());
        return aId - bId;
    }

    public List<NewsDto> getNewsByItem(String item, Integer limit) {
        String itemInInvesting = ITEMS_MAP.get(item.toLowerCase(Locale.ROOT)) != null
                ? ITEMS_MAP.get(item.toLowerCase(Locale.ROOT))
                : item;
        return collector.collect(itemInInvesting, limit);
    }
}
