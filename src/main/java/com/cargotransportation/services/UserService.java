package com.cargotransportation.services;

import com.cargotransportation.constants.UserStatus;
import com.cargotransportation.dto.AgentDto;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.requests.CreateAgentRequest;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.CreateShipperRequest;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto save(CreateCarrierRequest request);
    UserDto save(CreateShipperRequest request);
    AgentDto save(CreateAgentRequest request);
    List<UserDto> getUsers();
    void changeUserConfirmedStatus(Long userId, UserStatus status);
    UserDto findById(Long id);
    UserDto findByUsername(String username);
    List<TransportDto> getTransports();
    UserDto findUserByRoleAndId(String roleName,Long id);

    List<UserDto> getAgentCarries();

}
