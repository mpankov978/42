package ru.hackandchallenge.investadvisor.exception;

/**
 * Ошибка при обновлении данных актива
 */
public class AssetUpdateException extends RuntimeException {

    public AssetUpdateException() {
        super();
    }

    public AssetUpdateException(String message) {
        super(message);
    }
}
