package com.statbroker.backend.controller;

import com.statbroker.backend.dto.Auth.AuthDto;
import com.statbroker.backend.dto.Auth.LoginRequest;
import com.statbroker.backend.dto.Auth.RegisterRequest;
import com.statbroker.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<AuthDto> createAccount(@Valid @RequestBody RegisterRequest data){

        AuthDto response = authService.createAccount(data);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@Valid @RequestBody LoginRequest data) throws BadRequestException {

        AuthDto response = authService.login(data);
        return ResponseEntity.ok(response);



    }
}
