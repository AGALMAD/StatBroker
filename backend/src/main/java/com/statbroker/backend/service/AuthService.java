package com.statbroker.backend.service;

import com.statbroker.backend.config.security.jwt.JwtService;
import com.statbroker.backend.dto.Auth.AuthDto;
import com.statbroker.backend.dto.Auth.LoginRequest;
import com.statbroker.backend.dto.Auth.RegisterRequest;
import com.statbroker.backend.model.User;
import com.statbroker.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthDto createAccount(RegisterRequest data){

        if (userRepository.existsByEmail(data.getEmail())) {
            throw new IllegalArgumentException("Email already exist");
        }

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

    public AuthDto login(LoginRequest data) throws BadRequestException {
        Optional<User> user = userRepository.findByEmail(data.getEmail());

        if(user.isEmpty())
            throw new BadRequestException("User not found");

        if (!passwordEncoder.matches(data.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        return AuthDto.builder()
                .accessToken(jwtService.generateToken(user.get()))
                .refreshToken(jwtService.generateRefreshToken(user.get()))
                .build();
    }
}
