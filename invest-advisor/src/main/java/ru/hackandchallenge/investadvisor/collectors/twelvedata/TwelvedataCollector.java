package ru.hackandchallenge.investadvisor.collectors.twelvedata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hackandchallenge.investadvisor.dto.TwelvedataDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
public class TwelvedataCollector {

    private static final Pattern TWELVEDATA_DTO_PATTERN = Pattern.compile("\"\\w+\":(\\{\"meta\":\\{.*?},\"values\":\\[.*?],\"status\":\"ok\"}),?");
    private static final String SITE = "https://api.twelvedata.com";
    private static final String API_KEY = "997ed3a430e644949befeab17b59d302";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public Set<TwelvedataDto> getItems(Collection<String> items, String interval) {
        Set<TwelvedataDto> result = new HashSet<>();
        String symbols = String.join(",", items);
        RestTemplate restTemplate = new RestTemplate();
        String url = SITE + "/time_series?symbol=" + symbols + "&interval=" + interval + "&apikey=" + API_KEY + "&source=docs";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (OK.equals(response.getStatusCode())) {
            Matcher matcher = TWELVEDATA_DTO_PATTERN.matcher(response.getBody());
            while (matcher.find()) {
                result.add(OBJECT_MAPPER.readValue(matcher.group(1), TwelvedataDto.class));
            }
        } else {
            throw new RuntimeException();
        }
        return result;
    }

}
