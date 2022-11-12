package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.collectors.investing.InvestingNewsCollector;
import ru.hackandchallenge.investadvisor.dto.InvestPortfolioDto;
import ru.hackandchallenge.investadvisor.dto.investing.InvestingNewsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.hackandchallenge.investadvisor.controller.InvestingNewsController.ITEMS_MAP;

@Service
@AllArgsConstructor
public class InvestingNewsService {

    private static final Pattern ARTICLE_ID_PATTERN = Pattern.compile("\\d+$");

    public InvestingNewsCollector collector;
    public InvestPortfolioService portfolioService;

    public List<InvestingNewsDto> getClientNews(Long clientId) {
        InvestPortfolioDto dto = portfolioService.getInfo(clientId);
        List<InvestingNewsDto> news = dto.assets().stream().map(asset -> collector.collect(asset.code(), 3))
                .reduce(new ArrayList<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
        news.sort((a, b) -> -compare(a, b));
        return news;
    }

    private int compare(InvestingNewsDto a, InvestingNewsDto b) {
        Matcher idMatcher;
        idMatcher = ARTICLE_ID_PATTERN.matcher(a.getLink());
        idMatcher.find();
        int aId = Integer.parseInt(idMatcher.group());
        idMatcher = ARTICLE_ID_PATTERN.matcher(b.getLink());
        idMatcher.find();
        int bId = Integer.parseInt(idMatcher.group());
        return aId - bId;
    }

    public List<InvestingNewsDto> getNewsByItem(String item, Integer limit) {
        String itemInInvesting = ITEMS_MAP.get(item.toLowerCase(Locale.ROOT)) != null
                ? ITEMS_MAP.get(item.toLowerCase(Locale.ROOT))
                : item;
        return collector.collect(itemInInvesting, limit);
    }
}