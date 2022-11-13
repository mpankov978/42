package ru.hackandchallenge.investadvisor.aspects;

import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.hackandchallenge.investadvisor.infrastructure.ClientComponent;

@Aspect
@Component
@AllArgsConstructor
public class CheckRoleAspect {

    private final ClientComponent clientComponent;

    @Before("@annotation(ClientOnly)")
    public void checkClientRole() {
        checkRole("CLIENT");
    }

    private void checkRole(String role) {
        if (!clientComponent.getRole().equalsIgnoreCase(role)) {
            throw new RuntimeException();
        }
    }

    @Before("@annotation(OperatorOnly)")
    public void checkOperatorRole() {
        checkRole("OPERATOR");
    }
}
