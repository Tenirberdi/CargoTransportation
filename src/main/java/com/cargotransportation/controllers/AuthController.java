package com.cargotransportation.controllers;

import com.cargotransportation.dto.requests.CreateAgentRequest;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.CreateShipperRequest;
import com.cargotransportation.dto.requests.SignInRequest;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.dto.response.SignInResponse;
import com.cargotransportation.dto.response.SignUpResponse;
import com.cargotransportation.services.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;
    private final AuthService authService;
    private final FilesStorageService filesStorageService;

    public AuthController(UserService userService, RoleService roleService, AuthService authService, FilesStorageService filesStorageService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authService = authService;
        this.filesStorageService = filesStorageService;
    }

    @PostMapping("/sign-in")
    private Response authorize(@RequestBody SignInRequest signInRequest) {
        return new Response(SUCCESS,0,
                SignInResponse.builder()
                        .token(authService.authorize(signInRequest.getUsername(),
                        signInRequest.getPassword())).build());
    }

//    @GetMapping("/roles")
    private Response getRoles() {
        return new Response(SUCCESS, 0, roleService.getRoles());
    }

    @PostMapping("/sign-up/carrier")
    private Response registerShipper(@RequestBody CreateCarrierRequest request) {
        return new Response(SUCCESS, 0,
                userService.save(request));
    }

    @PostMapping("/sign-up/shipper")
    private Response registerCarrier(@RequestBody CreateShipperRequest request) {
        return new Response(SUCCESS, 0,
                userService.save(request));
    }

    @PostMapping("/sign-up/agent")
    public Response save(@RequestBody CreateAgentRequest request){
        return new Response(SUCCESS, 0, userService.save(request));
    }
}
