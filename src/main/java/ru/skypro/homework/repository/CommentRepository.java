package ru.skypro.homework.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.CommentEntity;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    Logger logger = LoggerFactory.getLogger(CommentRepository.class);

    default CommentEntity getCommentById(int id) {
        logger.info("Getting comment by id " + id);
        return findById(id).orElseThrow(() -> new NotFoundException("Comment by id " + id + " not found"));
    }

    Collection<CommentEntity> findAllByAdId(int id);
}