package ru.skypro.homework.controller;
import ru.skypro.homework.dto.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController  {

    @PostMapping("/users/set_password")//Обновление пароля
    public User setPasword(){
        return null;
    }
    @GetMapping("/users/me")//Получение информации об авторизованном пользователе
    public User getUser(){
        return null;
    }
    @PatchMapping("/users/me")//Обновление информации об авторизованном пользователе
    public User updateUser(){
    return null;
    }
    @PatchMapping("/users/me/image")//Обновление аватара авторизованного пользователя
    public Avatar updateUserImage(){
    return null;
    }
}
