package com.cargotransportation.controllers;

import com.cargotransportation.dto.UserDto;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    private final UserService userService;
    private final AuthService authService;

    public ProfileController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/profile")
    private ResponseEntity<UserDto> getProfile() {
        return ResponseEntity.ok(userService.findById(
                userService.findByUsername(authService.getCurrentUserUsername()).getId()
        ));
    }
}
