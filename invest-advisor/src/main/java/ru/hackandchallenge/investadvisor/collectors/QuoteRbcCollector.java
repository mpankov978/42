package ru.hackandchallenge.investadvisor.collectors;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.dto.NewsDto;

import java.util.List;

@Service
@AllArgsConstructor
public class QuoteRbcCollector {

    @SneakyThrows
    public List<NewsDto> collect(Integer limit) {
        Document doc = Jsoup.connect("https://quote.rbc.ru/tag/Dividends").get();
        Elements select = doc.select("body > div.l-window.l-window-overflow-mob > div.g-relative.g-clear > div.l-col-container > div.l-table > div:nth-child(3) > div > div.l-col-main > div.js-load-container");
        Elements titleSpans = select.first().getElementsByClass("q-item__title");
        return titleSpans.stream().limit(limit).map(QuoteRbcCollector::convert).toList();
    }

    private static NewsDto convert(Element titleSpan) {
        String title = titleSpan.text();
        Element titleLink = titleSpan.parent();
        String link = titleLink.attr("href");
        Element articleDiv = titleLink.parent();
        String preview = articleDiv.getElementsByClass("q-item__description").text();
        String from = articleDiv.getElementsByClass("q-item__date").text();
        return new NewsDto(title, link, preview, from);
    }

}
