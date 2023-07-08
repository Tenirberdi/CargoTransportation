package com.cargotransportation.services.impl;

import com.cargotransportation.constants.UserStatus;
import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Agent;
import com.cargotransportation.dao.Carrier;
import com.cargotransportation.dao.Role;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.AgentDto;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.requests.CreateAgentRequest;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.CreateShipperRequest;
import com.cargotransportation.exception.DuplicateEntryException;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.AgentRepository;
import com.cargotransportation.repositories.CarrierRepository;
import com.cargotransportation.repositories.RoleRepository;
import com.cargotransportation.repositories.TransportRepository;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CarrierRepository carrierRepository;
    private final TransportRepository transportRepository;
    private final AuthService authService;
    private final AgentRepository agentRepository;

    @Override
    public UserDto save(UserDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new DuplicateEntryException("Username is already taken");
        }
        userDto.setCreatedAt(LocalDateTime.now());
        User user = userRepository.save(Converter.convert(userDto));
        return Converter.convert(user);
    }

    @Override
    public UserDto save(CreateCarrierRequest request) {
        if(userRepository.findByUsername(request.getUsername()) != null) {
            throw new DuplicateEntryException("Username is already taken");
        }
        User currentUser = authService.getCurrentUser();
        User result;
        if (currentUser != null && currentUser.getRole().getName().equals("ROLE_AGENT")) {
            result = carrierRepository.save(Carrier.builder()
                    .username(request.getUsername())
                    .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                    .role(Role.builder().name("ROLE_CARRIER").build())
                    .createdAt(LocalDateTime.now())
                    .fio(request.getFio())
                    .birthDate(request.getBirthDate())
                    .address(request.getAddress())
                    .phone(request.getPhone())
                    .agent(agentRepository.findById(currentUser.getId()).get())
                    .build());
        } else {
            result = userRepository.save(User.builder()
                    .username(request.getUsername())
                    .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                    .role(Role.builder().name("ROLE_CARRIER").build())
                    .createdAt(LocalDateTime.now())
                    .fio(request.getFio())
                    .birthDate(request.getBirthDate())
                    .address(request.getAddress())
                    .phone(request.getPhone())
                    .build());
        }
        return Converter.convert(result);
    }

    @Override
    public UserDto save(CreateShipperRequest request) {
        if(userRepository.findByUsername(request.getUsername()) != null) {
            throw new DuplicateEntryException("Username is already taken");
        }
        User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(Role.builder().name("ROLE_SHIPPER").build())
                .createdAt(LocalDateTime.now())
                .fio(request.getFio())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .phone(request.getPhone()).build());

        return Converter.convert(user);
    }

    @Override
    @Transactional
    public void changeUserConfirmedStatus(Long userId, UserStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));
        user.setConfirmed(status.getValue());
    }

    @Override
//    @PreAuthorize("hasAuthority('user.read')")
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "User with id " + id + " not found!"));
        return Converter.convert(user);
    }

    @Override
//    @PreAuthorize("hasAuthority('user.read')")
    public UserDto findByUsername(String username) {
        return Converter.convert(userRepository.findByUsername(username));
    }

    @Override
//    @PreAuthorize("hasAuthority('user.read')")
    public List<TransportDto> getTransports() {
        return transportRepository.findById(authService.getCurrentUser().getId())
                .stream().map(Converter::convert).collect(Collectors.toList());
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
    public List<UserDto> getAgentCarries() {
        return carrierRepository.findAllByAgent_Id(authService.getCurrentUser().getId())
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AgentDto save(CreateAgentRequest request) {
        Agent agent = Agent.builder()
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(Role.builder().name("ROLE_AGENT").build())
                .createdAt(LocalDateTime.now())
                .fio(request.getFio())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .phone(request.getPhone())
                .companyName(request.getCompanyName())
                .companyAddress(request.getCompanyAddress())
                .build();
        return Converter.convert(agentRepository.save(agent));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(Converter::convert).collect(Collectors.toList());
    }

    private boolean ifUsernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

}
