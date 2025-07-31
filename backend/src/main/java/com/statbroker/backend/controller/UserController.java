package com.statbroker.backend.controller;

import com.statbroker.backend.dto.User.UserDto;
import com.statbroker.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserDto getAuthenticatedUser(Authentication auth){
        return userService.getUserByEmail(auth.getName());
    }
}
