package ru.hackandchallenge.investadvisor.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ClientComponent {

    private Long id;
    private String role;

}
