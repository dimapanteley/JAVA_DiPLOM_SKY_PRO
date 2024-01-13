package ru.skypro.homework.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
    }

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
