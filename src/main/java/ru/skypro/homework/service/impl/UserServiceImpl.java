package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.PasswordMatchingException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageServiceImpl imageService;
    private final PasswordEncoder encoder;


    public UserServiceImpl(UserRepository userRepository, ImageServiceImpl imageService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.encoder = encoder;
    }

    @Override
    public void setPassword(NewPassword newPassword, Authentication authentication) {
        log.info("Run method setPassword in service");
        String oldPassword = newPassword.getCurrentPassword();
        String encodedNewPassword = encoder.encode(newPassword.getNewPassword());
        UserEntity user = userRepository.getByUsername(authentication.getName());
        if (encoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(encodedNewPassword);
        } else {
            throw new PasswordMatchingException("Wrong current password");
        }
        userRepository.save(user);
    }

    @Override
    public User getUser(Authentication authentication) {
        log.info("Run method getUser in service");
        return UserMapper.toUser(userRepository.getByUsername(authentication.getName()));
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        log.info("Run method updateUser in service");
        UserEntity user = userRepository.getByUsername(authentication.getName());
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        return UserMapper.toUpdateUser(userRepository.save(user));
    }

    @Transactional
    @Override
    public void updateUserImage(MultipartFile file, Authentication authentication) throws IOException {
        log.info("Run method updateUserImage in service");
        UserEntity user = userRepository.getByUsername(authentication.getName());
        if (user.getImage() != null) {
            imageService.removeImageById(user.getImage().getId());
        }
        user.setImage(imageService.updateImage(file));
        userRepository.save(user);
    }
}