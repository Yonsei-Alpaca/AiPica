package com.alpaca.AiPica.auth.service;

import com.alpaca.AiPica.auth.dto.UserRequest;
import com.alpaca.AiPica.auth.entity.UserEntity;
import com.alpaca.AiPica.auth.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

@Service
@RequiredArgsConstructor

public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public UserEntity userRegister(
            UserRequest userRequest
    ) {

        if (userRepository.existsByUserName(userRequest.getUserName())) {
            throw new DataIntegrityViolationException("이미 존재하는 User ID 입니다: " + userRequest.getUserName());
        }

        var entity = UserEntity.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword1()))
                .build()
                ;

        return userRepository.save(entity);
    }
}
