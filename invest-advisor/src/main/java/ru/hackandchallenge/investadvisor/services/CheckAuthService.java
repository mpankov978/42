package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hackandchallenge.investadvisor.dto.auth.CheckAuthRequest;
import ru.hackandchallenge.investadvisor.dto.auth.CheckAuthResponse;
import ru.hackandchallenge.investadvisor.exception.AuthException;

import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
public class CheckAuthService {

    private static final String AUTH_URL = "https://hack.invest-open.ru/jwt/verify";

    public CheckAuthResponse checkAuth(String jwt) {
        RestTemplate restTemplate = new RestTemplate();
        CheckAuthRequest request = new CheckAuthRequest(jwt.replace("Bearer ", ""));
        ResponseEntity<CheckAuthResponse> response = restTemplate
                .postForEntity(AUTH_URL, request, CheckAuthResponse.class);
        if (OK.equals(response.getStatusCode())) {
            return response.getBody();
        } else {
            throw new AuthException("Ошибка при аутентификации");
        }
    }
}
