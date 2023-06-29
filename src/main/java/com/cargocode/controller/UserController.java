package com.cargocode.controller;

import com.cargocode.model.dto.UserDto;
import com.cargocode.model.mapper.UserMapper;
import com.cargocode.model.request.CreateUserRequest;
import com.cargocode.model.response.UserResponse;
import com.cargocode.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController
{

    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest request){
        UserDto user = userService.registerUser(request);

        return new ResponseEntity<>(UserMapper.INSTANCE.dtoToResponse(user), HttpStatus.OK);
    }

}
