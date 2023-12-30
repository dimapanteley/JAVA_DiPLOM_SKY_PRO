package ru.skypro.homework.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;

import java.util.Collection;

public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    Logger logger = LoggerFactory.getLogger(AdRepository.class);

    default AdEntity getAdById(int id) {
        logger.info("Getting ad by id " + id);
        return findById(id).orElseThrow(() -> new NotFoundException("Ad by id " + id + " not found"));
    }

    Collection<AdEntity> findAllByAuthor(UserEntity author);
}