package com.cargocode.service.impl;

import com.cargocode.exception.user.UserIsExists;
import com.cargocode.model.dto.UserDto;
import com.cargocode.model.entity.User;
import com.cargocode.model.mapper.UserMapper;
import com.cargocode.model.request.CreateUserRequest;
import com.cargocode.repository.UserRepository;
import com.cargocode.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto registerUser(CreateUserRequest request) {

        if(userRepository.findUserByEmail(request.getEmail())!=null){
            throw new UserIsExists(
                    "Пользователь с email адресом '" + request.getEmail() + "' уже существует!",
                    HttpStatus.BAD_REQUEST);
        }
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .contactNumber(request.getContactNumber())
                .company(request.getCompany())
                .mcDotNumber(request.getMcDotNumber())
                .physicalAddress(request.getPhysicalAddress())
                .role(request.getRole())
                .build();

        return UserMapper.INSTANCE.toDto(userRepository.save(user));
    }

}
