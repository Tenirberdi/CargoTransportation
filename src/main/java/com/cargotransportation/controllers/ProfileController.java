package com.cargotransportation.controllers;

import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {
    private final UserService userService;
    private final AuthService authService;

    public ProfileController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/profile")
    public UserDto getProfile() {
        return userService.findById(
                userService.findByUsername(authService.getCurrentUserUsername()).getId());
    }

    @GetMapping("/transports")
    public List<TransportDto> getTransports() {
        return userService.getTransports();
    }
}
