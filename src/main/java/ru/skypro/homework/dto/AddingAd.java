package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddingAd {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private CreateOrUpdateAd properties;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile image;
}