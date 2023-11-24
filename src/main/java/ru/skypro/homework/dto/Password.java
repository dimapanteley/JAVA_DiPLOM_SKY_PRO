package ru.skypro.homework.dto;

import lombok.Data;

@Data
    public class Password {
        private String currentPassword; // текущий пароль
        private String newPassword; // новый пароль
    }
