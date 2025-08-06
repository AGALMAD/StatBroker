package com.statbroker.backend.controller;

import com.statbroker.backend.dto.auth.AuthDto;
import com.statbroker.backend.dto.auth.LoginRequest;
import com.statbroker.backend.dto.auth.RegisterRequest;
import com.statbroker.backend.service.AuthService;
import com.statbroker.backend.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;


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

    @GetMapping("/oauth")
    public ResponseEntity<AuthDto> loginSuccess(@AuthenticationPrincipal OAuth2User oauthUser) {
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");


        AuthDto auth = authService.oauthLogin(email,name);

        return ResponseEntity.ok(auth);
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthDto> refreshToken(Authentication auth, @RequestBody AuthDto data) throws BadRequestException, AccessDeniedException {

        AuthDto response = authService.refreshToken(data,auth.getName());
        return ResponseEntity.ok(response);

    }

    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestParam String userEmail) {
        emailService.sendVerificationEmail(userEmail, authService.generateVerificationCode(userEmail));
        return ResponseEntity.ok("Codigo enviado al correo " + userEmail);
    }

}
