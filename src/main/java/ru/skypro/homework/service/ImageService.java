package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.ImageEntity;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {
    ImageEntity updateImage(MultipartFile file) throws IOException;

    void saveImageToFolder(MultipartFile image, Path filePath) throws IOException;

    String getExtension(String fileName);

    void removeImageById(int id) throws IOException;

    byte[] getImage(int id);
}
