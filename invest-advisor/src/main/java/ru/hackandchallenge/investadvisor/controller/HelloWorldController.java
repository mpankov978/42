package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Тестовый контроллер", description = "API для проверки работоспособности приложения")
public class HelloWorldController {

    @GetMapping(path = "/hello")
    @Operation(description = "Тестовая страница")
    @ResponseBody
    public String getPage() {
        return "Hello World!";
    }


}
