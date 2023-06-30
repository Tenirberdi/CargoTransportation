package com.cargotransportation.services;

import com.cargotransportation.dto.RoleDto;

public interface RoleService {
    RoleDto findByName(String name);
}
