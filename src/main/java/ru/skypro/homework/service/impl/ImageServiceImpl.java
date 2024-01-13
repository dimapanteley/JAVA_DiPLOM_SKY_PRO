package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Value("${images.folder}")
    String folderPath;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional
    public ImageEntity updateImage(MultipartFile file) throws IOException {
        log.info("Run method updateImage in service");
        ImageEntity image = new ImageEntity();
        image = imageRepository.save(image);
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            fileName = "";
        }
        Path filePath = Path.of(folderPath, image.getId() + "." + getExtension(fileName));
        image.setFilePath(filePath.toString());
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        saveImageToFolder(file, filePath);
        return imageRepository.save(image);
    }

    @Override
    public void saveImageToFolder(MultipartFile file, Path filePath) throws IOException {
        log.info("Run method saveImageToFolder in service");
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
    }

    @Override
    public String getExtension(String fileName) {
        log.info("Run method getExtension in service");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void removeImageById(int id) throws IOException {
        log.info("Run method removeImageById");
        ImageEntity image = imageRepository.getImageById(id);
        imageRepository.delete(image);
        Files.delete(Path.of(image.getFilePath()));
    }

    @Override
    public byte[] getImage(int id) {
        ImageEntity entity = imageRepository.getImageById(id);
        Path path = Path.of(entity.getFilePath());
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            return entity.getData();
        }
    }
}