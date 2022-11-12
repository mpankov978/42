package ru.hackandchallenge.investadvisor.exception;

/**
 * Ошибка "Актив не существует в инвестиционном портфеле"
 */
public class AssetOperationException extends RuntimeException {

    public AssetOperationException() {
        super();
    }

    public AssetOperationException(String message) {
        super(message);
    }
}
