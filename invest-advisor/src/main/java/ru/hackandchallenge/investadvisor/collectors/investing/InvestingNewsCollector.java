package ru.hackandchallenge.investadvisor.collectors.investing;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.dto.investing.InvestingNewsDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestingNewsCollector {

    private static final String SITE = "https://ru.investing.com";

    @SneakyThrows
    public List<InvestingNewsDto> collect(String item, Integer limit) {
        Document doc = Jsoup.connect(SITE + "/equities/" + item + "-news").get();
        Elements select = doc.select("#leftColumn > div.mediumTitle1");
        if (select != null && select.first() != null) {
            return select.first().getElementsByClass("js-article-item articleItem   ")
                    .stream().limit(limit)
                    .map(this::parseArticle)
                    .collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    private InvestingNewsDto parseArticle(Element article) {
        final InvestingNewsDto investingNewsDto = new InvestingNewsDto();
        Element firstArticle = article.getElementsByClass("textDiv").first();
        investingNewsDto.setPreview(firstArticle.getElementsByTag("p").text());
        Element href = firstArticle.getElementsByAttribute("href").first();
        investingNewsDto.setTitle(href.text());
        investingNewsDto.setLink(SITE + href.attributes().get("href"));
        Element details = firstArticle.getElementsByClass("articleDetails").first();
        investingNewsDto.setFrom(details.getElementsByTag("span").get(0).text());
        return investingNewsDto;
    }

}
