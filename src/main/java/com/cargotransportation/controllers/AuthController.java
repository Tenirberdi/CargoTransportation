package com.cargotransportation.controllers;

import com.cargotransportation.dto.requests.CreateCarrierCompanyRequest;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.CreateUserRequest;
import com.cargotransportation.dto.requests.SignInRequest;
import com.cargotransportation.dto.response.SignInResponse;
import com.cargotransportation.dto.response.SignUpResponse;
import com.cargotransportation.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private SignInResponse authorize(@RequestBody SignInRequest signInRequest) {
        return SignInResponse.builder()
                .token(authService.authorize(signInRequest.getUsername(), signInRequest.getPassword())).build();
    }

    @GetMapping("/roles")
    private List<String> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping("/sign-up/carrier")
    private SignUpResponse registerShipper(@RequestBody CreateCarrierRequest request) {
        return SignUpResponse.builder().id(userService.save(request)).build();
    }

    @PostMapping("/sign-up/shipper")
    private SignUpResponse registerCarrier(@RequestBody CreateUserRequest request) {
        return SignUpResponse.builder().id(userService.save(request)).build();
    }

    @PostMapping("/sign-up/company-owner")
    public ResponseEntity<?> save(@RequestBody CreateCarrierCompanyRequest request){
        return new ResponseEntity<>(userService.save(request), HttpStatus.OK);
    }
}
