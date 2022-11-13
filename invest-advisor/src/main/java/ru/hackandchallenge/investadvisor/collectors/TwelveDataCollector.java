package ru.hackandchallenge.investadvisor.collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hackandchallenge.investadvisor.dto.quotes.TwelveDataDto;
import ru.hackandchallenge.investadvisor.exception.DataCollectorException;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
public class TwelveDataCollector {

    private static final Pattern TWELVEDATA_DTO_PATTERN = Pattern.compile("\"\\w+\":(\\{\"meta\":\\{.*?},\"values\":\\[.*?],\"status\":\"ok\"}),?");
    private static final String SITE = "https://api.twelvedata.com";
    private static final String API_KEY = "997ed3a430e644949befeab17b59d302";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

    @SneakyThrows
    public Set<TwelveDataDto> getItems(Collection<String> items, String interval) {
        Set<TwelveDataDto> result = new HashSet<>();
        String symbols = String.join(",", items);
        RestTemplate restTemplate = new RestTemplate();
        String url = SITE + "/time_series?symbol=" + symbols + ",&interval=" + interval + "&apikey=" + API_KEY + "&source=docs";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (OK.equals(response.getStatusCode())) {
            Matcher dtoMatcher = TWELVEDATA_DTO_PATTERN.matcher(response.getBody());
            while (dtoMatcher.find()) {
                result.add(OBJECT_MAPPER.readValue(dtoMatcher.group(1), TwelveDataDto.class));
            }
        } else {
            throw new DataCollectorException("Ошибка при сборе данных коллектором");
        }
        return result;
    }

}
