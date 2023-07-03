package com.cargotransportation.dto;

import com.cargotransportation.dao.Role;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String role;
    private LocalDateTime createdAt;
    private boolean isConfirmed;
    private String fio;
    private Integer age;
    private String address;
    private String phone;
}
