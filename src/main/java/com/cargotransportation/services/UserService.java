package com.cargotransportation.services;

import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.response.UserResponse;

public interface UserService {
    void save(UserDto userDto);
    UserDto findById(Long id);
    UserDto findUserByRoleAndId(String roleName,Long id);

}
