package com.cargotransportation.dto.response;

import com.cargotransportation.dao.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String username;
    private Role role;
    private LocalDateTime createdAt;
    private boolean isConfirmed;
}
