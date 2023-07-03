package com.cargotransportation.dto.requests;

import com.cargotransportation.constants.TransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarrierRequest {
    private String username;
    private String password;
    private String role;
    private String fio;
    private String address;
    private String phone;
    private Integer age;
    private String autoModel;
    private String autoNumber;
    private Integer autoCapacityInTons;
    private TransportType autoType;
}
