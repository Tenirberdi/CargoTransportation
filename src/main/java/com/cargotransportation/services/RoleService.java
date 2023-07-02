package com.cargotransportation.services;

import com.cargotransportation.dto.RoleDto;

import java.util.List;

public interface RoleService {
    String findByName(String name);

    List<String> getRoles();
}
