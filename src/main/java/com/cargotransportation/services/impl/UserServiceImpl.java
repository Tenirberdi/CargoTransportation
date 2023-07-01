package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.response.UserResponse;
import com.cargotransportation.exception.user.UserNotFound;
import com.cargotransportation.repositories.RoleRepository;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(UserDto userDto) {
        if(usernameIsExists(userDto.getUsername()))
        {
            throw new UserNotFound(
                    "User with username '" + userDto.getUsername() + " is exists!",
                    HttpStatus.CONFLICT);
        }
        User user = User.builder()
                .username(userDto.getUsername())
                // TODO: encode password when add security dependency
                .password(userDto.getPassword())
//                .role()
                .build();
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(
                "User with id " + id + " not found!",
                HttpStatus.NOT_FOUND));
        return Converter.convert(user);
    }

    private boolean usernameIsExists(String username){
        return userRepository.findByUsername(username) != null;
    }

}
