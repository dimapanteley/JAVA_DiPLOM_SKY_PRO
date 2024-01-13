package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.exception.UserAlreadyRegisteredException;
import ru.skypro.homework.exception.WrongPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final MyUserDetailsService userDetailService;
    private final UserRepository userRepository;

    public AuthServiceImpl(PasswordEncoder encoder,
                           MyUserDetailsService userDetailService,
                           UserRepository userRepository) {
        this.encoder = encoder;
        this.userDetailService = userDetailService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        log.info("Run method login in service");
        UserDetails userDetails = userDetailService.loadUserByUsername(userName);
        if (encoder.matches(password, userDetails.getPassword())) {
            return true;
        }
        throw new WrongPasswordException("Wrong password");
    }

    @Override
    public boolean register(Register register) {
        log.info("Run method register in service");
        UserEntity user = UserMapper.toUserEntity(register);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyRegisteredException("User by username " + user.getUsername() + " already registered");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}