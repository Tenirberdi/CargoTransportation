package com.cargocode.service;

import com.cargocode.model.dto.UserDto;
import com.cargocode.model.entity.User;
import com.cargocode.model.request.CreateUserRequest;
import com.cargocode.model.utils.UserRole;

public interface UserService {
    UserDto registerUser(CreateUserRequest request);
}
