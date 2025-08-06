package com.statbroker.backend.service;

import com.statbroker.backend.config.security.jwt.JwtService;
import com.statbroker.backend.dto.auth.AuthDto;
import com.statbroker.backend.dto.auth.LoginRequest;
import com.statbroker.backend.dto.auth.RegisterRequest;
import com.statbroker.backend.model.User;
import com.statbroker.backend.model.VerificationCode;
import com.statbroker.backend.repository.UserRepository;
import com.statbroker.backend.repository.VerificationCodeRepository;
import com.statbroker.backend.util.CodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final int MINUTES_TO_EXPIRATION = 5;


    public AuthDto createAccount(RegisterRequest data){

        if (userRepository.existsByEmail(data.getEmail())) {
            throw new UsernameNotFoundException("Email already exist");
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
            throw new UsernameNotFoundException("User not found");

        if (!passwordEncoder.matches(data.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        return AuthDto.builder()
                .accessToken(jwtService.generateToken(user.get()))
                .refreshToken(jwtService.generateRefreshToken(user.get()))
                .build();
    }

    public AuthDto oauthLogin(String email, String name) {

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(email)
                            .name(name)
                            .build();
                    return userRepository.save(newUser);
                });

        return AuthDto.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();

    }



    public AuthDto refreshToken(AuthDto tokens, String userEmail) throws BadRequestException, AccessDeniedException {

        Optional<User> user = userRepository.findByEmail(userEmail);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");


        if (!jwtService.isTokenValid(tokens.getAccessToken(), user.get() ) | !jwtService.isTokenValid(tokens.getRefreshToken(), user.get() )){
            throw new AccessDeniedException("Tokens are not valid");
        }

        return AuthDto.builder()
                .accessToken(jwtService.generateToken(user.get()))
                .refreshToken(tokens.getRefreshToken())
                .build();

    }

    @Transactional
    public String generateVerificationCode(String userEmail){

        User actualUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<VerificationCode> existingCode = verificationCodeRepository.findByUser(actualUser);

        if (existingCode.isPresent()) {
            if (existingCode.get().getExpiresAt().isAfter(LocalDateTime.now())) {
                return existingCode.get().getCode();
            } else {
                verificationCodeRepository.delete(existingCode.get());
            }
        }


        VerificationCode newVerificationCode = VerificationCode.builder()
                .code(CodeGenerator.generateCode())
                .user(actualUser)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(MINUTES_TO_EXPIRATION))
                .build();


        verificationCodeRepository.save(newVerificationCode);

        return newVerificationCode.getCode();

    }
}
