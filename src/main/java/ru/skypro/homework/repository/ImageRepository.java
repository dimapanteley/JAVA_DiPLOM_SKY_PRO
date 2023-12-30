package ru.skypro.homework.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    Logger logger = LoggerFactory.getLogger(ImageRepository.class);

    default ImageEntity getImageById(int id) {
        logger.info("Getting image by id " + id);
        return findById(id).orElseThrow(() -> new NotFoundException("Image by id " + id + " not found"));
    }
}