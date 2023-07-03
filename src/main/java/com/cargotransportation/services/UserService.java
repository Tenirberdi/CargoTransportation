package com.cargotransportation.services;

import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.CreateUserRequest;

import java.util.List;

public interface UserService {
    Long save(UserDto userDto);
    Long save(CreateCarrierRequest request);
    Long save(CreateUserRequest request);
    UserDto findById(Long id);
    UserDto findByUsername(String username);
    List<TransportDto> getTransports();
    UserDto findUserByRoleAndId(String roleName,Long id);
}
