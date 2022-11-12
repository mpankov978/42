package ru.hackandchallenge.investadvisor.exception;

/**
 * Ошибка при аутентификации
 */
public class AuthException extends RuntimeException {

    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }
}
