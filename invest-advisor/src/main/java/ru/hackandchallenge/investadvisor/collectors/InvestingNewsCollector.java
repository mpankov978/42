package ru.hackandchallenge.investadvisor.collectors;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.dto.NewsDto;

import java.util.Collections;
import java.util.List;

@Service
public class InvestingNewsCollector {

    private static final String SITE = "https://ru.investing.com";

    @SneakyThrows
    public List<NewsDto> collect(String item, Integer limit) {
        Document doc = Jsoup.connect(SITE + "/equities/" + item + "-news").get();
        Elements select = doc.select("#leftColumn > div.mediumTitle1");
        if (select != null && select.first() != null) {
            return select.first().getElementsByClass("js-article-item articleItem   ")
                    .stream().limit(limit)
                    .map(this::parseArticle)
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    private NewsDto parseArticle(Element article) {
        final NewsDto newsDto = new NewsDto();
        Element firstArticle = article.getElementsByClass("textDiv").first();
        newsDto.setPreview(firstArticle.getElementsByTag("p").text());
        Element href = firstArticle.getElementsByAttribute("href").first();
        newsDto.setTitle(href.text());
        newsDto.setLink(SITE + href.attributes().get("href"));
        Element details = firstArticle.getElementsByClass("articleDetails").first();
        newsDto.setFrom(details.getElementsByTag("span").get(0).text());
        return newsDto;
    }

}
