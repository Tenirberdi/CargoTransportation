package com.cargotransportation.dto;

import com.cargotransportation.dao.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
    private boolean isConfirmed;
}
