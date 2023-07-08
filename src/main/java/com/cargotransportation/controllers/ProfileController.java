package com.cargotransportation.controllers;

import com.cargotransportation.constants.ResponseState;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
public class ProfileController {
    private final UserService userService;
    private final AuthService authService;

    public ProfileController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/profile")
    public Response getProfile() {
        UserDto user = userService.findById(authService.getCurrentUser().getId());
        return new Response(SUCCESS, 0, user);
    }

    @GetMapping("/transports")
    public Response getTransports() {
        return new Response(SUCCESS, 0,  userService.getTransports());
    }
}
