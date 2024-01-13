package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

public class AdMapper {
    public static Ad toAd(AdEntity entity) {
        Ad dto = new Ad();
        dto.setPk(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        if (entity.getImage() != null) {
            dto.setImage("/photo/image/" + entity.getImage().getId());
        }
        dto.setAuthor(entity.getAuthor().getId());
        return dto;
    }

    public static ExtendedAd toExtendedAd(AdEntity entity) {
        ExtendedAd dto = new ExtendedAd();
        dto.setPk(entity.getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getUsername());
        if (entity.getImage() != null) {
            dto.setImage("/photo/image/" + entity.getImage().getId());
        }
        dto.setPhone(entity.getAuthor().getPhone());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }
}
