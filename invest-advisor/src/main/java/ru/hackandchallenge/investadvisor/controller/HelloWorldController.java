package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

import static org.apache.naming.ResourceRef.AUTH;

@RestController
@AllArgsConstructor
@Tag(name = "Тестовый контроллер", description = "API для проверки работоспособности приложения")
public class HelloWorldController {

    @GetMapping(path = "/hello")
    @Operation(description = "Тестовая страница")
    @ResponseBody
    public Integer getPage(@RequestAttribute("clientId") Integer clientId) {
        return clientId;
    }


}
