package com.cargotransportation.dto.requests;

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
public class CreateUserRequest {
    private String username;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private boolean isConfirmed;
}
