package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.CarrierCompany;
import com.cargotransportation.dao.Role;
import com.cargotransportation.dao.Transport;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.CarrierCompanyDto;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.requests.CreateCarrierCompanyRequest;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.CreateUserRequest;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.CarrierCompanyRepository;
import com.cargotransportation.repositories.RoleRepository;
import com.cargotransportation.repositories.TransportRepository;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.RoleService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TransportRepository transportRepository;
    private final AuthService authService;
    private final CarrierCompanyRepository carrierCompanyRepository;

    @Override
    public Long save(UserDto userDto) {
        if(ifUsernameExists(userDto.getUsername()))
        {
            throw new NotFoundException(
                    "User with username '" + userDto.getUsername() + " is exists!");
        }
        userDto.setCreatedAt(LocalDateTime.now());
        return userRepository.save(Converter.convert(userDto)).getId();
    }

    @Override
    public Long save(CreateCarrierRequest request) {
        User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(Role.builder().name(request.getRole()).build())
                .createdAt(LocalDateTime.now())
                .fio(request.getFio())
                .age(request.getAge())
                .address(request.getAddress())
                .phone(request.getPhone()).build());
        return user.getId();
    }

    @Override
    public Long save(CreateUserRequest request) {
        return userRepository.save(User.builder()
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(Role.builder().name(request.getRole()).build())
                .createdAt(LocalDateTime.now())
                .fio(request.getFio())
                .age(request.getAge())
                .address(request.getAddress())
                .phone(request.getPhone()).build()).getId();
    }

    @Override
    @PreAuthorize("hasAuthority('user.read')")
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "User with id " + id + " not found!"));
        return Converter.convert(user);
    }

    @Override
    @PreAuthorize("hasAuthority('user.read')")
    public UserDto findByUsername(String username) {
        return Converter.convert(userRepository.findByUsername(username));
    }

    @Override
    @PreAuthorize("hasAuthority('user.read')")
    public List<TransportDto> getTransports() {
        return transportRepository.findById(
                findByUsername(authService.getCurrentUserUsername()).getId()
        ).stream().map(Converter::convert).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByRoleAndId(String roleName,Long id) {
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findByRoleAndId(role,id);
        if(user == null) throw new NotFoundException(
                role.getName()+" with id " + id + " not found!");
        return Converter.convert(user);
    }

    @Override
    public CarrierCompanyDto save(CreateCarrierCompanyRequest request) {
        Role role = roleRepository.findByName(request.getRole());
        CarrierCompany carrierCompany = CarrierCompany.builder()
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(role)
                .createdAt(LocalDateTime.now())
                .fio(request.getFio())
                .age(request.getAge())
                .address(request.getAddress())
                .phone(request.getPhone())

                .companyName(request.getCompanyName())
                .companyAddress(request.getCompanyAddressDto())
                .build();
        return Converter.convert(carrierCompanyRepository.save(carrierCompany));
    }



    private boolean ifUsernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

}
