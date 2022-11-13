package ru.hackandchallenge.investadvisor.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Данные о субъекте авторизации
 */
@Component
@Getter
@Setter
public class ClientComponent {

    private Long id;
    private String role;

}
