package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class Ads {
    private int count;
    private Collection<Ad> results;
}
