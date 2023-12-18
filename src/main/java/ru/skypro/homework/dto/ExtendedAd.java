package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {
    private int pk; //id объявления
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email; //логин автора объявления
    private String image; //ссылка на картинку объявления
    private String phone; //телефон автора объявления
    private int price;
    private String title; //заголовок объявления
}
