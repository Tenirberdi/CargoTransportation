package com.cargotransportation.services;

import com.cargotransportation.dao.Role;
import com.cargotransportation.dto.RoleDto;
import com.cargotransportation.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public RoleDto findByName(String name) {

        return null;
    }
}
