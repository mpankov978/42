package ru.hackandchallenge.investadvisor.exception;

/**
 * Ошибка при выполнении операций со счетом
 */
public class BalanceOperationException extends RuntimeException {

    public BalanceOperationException() {
        super();
    }

    public BalanceOperationException(String message) {
        super(message);
    }
}
