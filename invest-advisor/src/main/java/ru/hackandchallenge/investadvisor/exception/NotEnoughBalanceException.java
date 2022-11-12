package ru.hackandchallenge.investadvisor.exception;

/**
 * Ошибка "Недостаточно средств на счету"
 */
public class NotEnoughBalanceException extends RuntimeException {

    public NotEnoughBalanceException() {
        super();
    }

    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
