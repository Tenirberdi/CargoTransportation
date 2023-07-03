package com.cargotransportation.services.impl;

import com.cargotransportation.dao.Role;
import com.cargotransportation.dto.RoleDto;
import com.cargotransportation.repositories.RoleRepository;
import com.cargotransportation.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public String findByName(String name) {
        return roleRepository.findByName(name).getName();
    }

    @Override
    public List<String> getRoles() {
        return roleRepository.findAll().stream().map(Role::getName).collect(Collectors.toList());
    }
}
