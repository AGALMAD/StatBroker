package com.statbroker.backend.controller;

import com.statbroker.backend.dto.Auth.AuthDto;
import com.statbroker.backend.dto.Auth.RegisterRequest;
import com.statbroker.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    @PostMapping("/singup")
    public ResponseEntity<AuthDto> createAccount(@Valid @RequestBody RegisterRequest data){

        AuthDto response = authService.createAccount(data);
        return ResponseEntity.ok(response);


    }
}
