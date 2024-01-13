package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.ImageServiceImpl;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
    private final ImageServiceImpl imageService;

    @GetMapping("/photo/image/{imageId}")
    public byte[] getImage(@PathVariable int imageId) {
        log.info("Run method getImage");
        return imageService.getImage(imageId);
    }
}