package com.statbroker.backend.service;

import com.statbroker.backend.config.security.jwt.JwtService;
import com.statbroker.backend.dto.Auth.AuthDto;
import com.statbroker.backend.dto.Auth.RegisterRequest;
import com.statbroker.backend.model.User;
import com.statbroker.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    public AuthDto createAccount(RegisterRequest data){

        User user = User.builder()
                        .name(data.getName())
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .build();


        User newUser = userRepository.save(user);

        return AuthDto.builder()
        .accessToken(jwtService.generateToken(newUser))
        .refreshToken(jwtService.generateRefreshToken(newUser))
        .build();

    }
}
