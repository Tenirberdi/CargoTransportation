package com.cargotransportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDate birthDate;
    private String address;
    private String phone;
}
