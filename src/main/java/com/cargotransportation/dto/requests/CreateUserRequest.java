package com.cargotransportation.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String username;
    private String password;
    private String role;
    private String fio;
    private String address;
    private String phone;
    private Integer age;
}
