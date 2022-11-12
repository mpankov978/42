package ru.hackandchallenge.investadvisor.exception;

/**
 * Ошибка при выполнения операции с активом
 */
public class AssetOperationException extends RuntimeException {

    public AssetOperationException() {
        super();
    }

    public AssetOperationException(String message) {
        super(message);
    }
}
