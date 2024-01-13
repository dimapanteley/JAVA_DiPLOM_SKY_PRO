package ru.skypro.homework.exception;

public class PasswordMatchingException extends RuntimeException {
    public PasswordMatchingException() {
    }

    public PasswordMatchingException(String message) {
        super(message);
    }
}
