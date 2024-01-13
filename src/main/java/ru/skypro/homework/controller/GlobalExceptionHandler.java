package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.exception.UserAlreadyRegisteredException;
import ru.skypro.homework.exception.WrongPasswordException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String notFoundExceptionHandler(RuntimeException e) {
        log.info("Error: {}", e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(WrongPasswordException.class)
    public String wrongPasswordExceptionHandler(RuntimeException e) {
        log.info("Error: {}", e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public String userAlreadyRegisteredExceptionHandler(RuntimeException e) {
        log.info("Error: {}", e.getMessage(), e);
        return e.getMessage();
    }
}