package ru.hackandchallenge.investadvisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackandchallenge.investadvisor.aspects.ClientOnly;
import ru.hackandchallenge.investadvisor.dto.NotificationDto;
import ru.hackandchallenge.investadvisor.services.NotificationsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
@Tag(name = "Контроллер уведомлений", description = "API для получения уведомлений")
public class NotificationsController {

    private final NotificationsService notificationsService;

    @ClientOnly
    @GetMapping
    @Operation(description = "Получить список всех уведомлений у текущего клиента")
    public List<NotificationDto> getNotifications(@RequestAttribute("clientId") Long clientId) {
        return notificationsService.getAllByClientId(clientId);
    }


}
