package ru.hackandchallenge.investadvisor.exception;

/**
 * Ошибка при сборе данных коллектором
 */
public class DataCollectorException extends RuntimeException {

    public DataCollectorException() {
        super();
    }

    public DataCollectorException(String message) {
        super(message);
    }
}
