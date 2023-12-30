package ru.skypro.homework.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Transactional
    default UserEntity getByUsername(String username) {
        logger.info("Getting user by username " + username);
        return findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User by username " + username + " not found"));
    }

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}