package com.cargotransportation.dto.requests;

import com.cargotransportation.dto.AddressDto;
import com.cargotransportation.dto.TransportDto;
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
public class CreateCarrierCompanyRequest {

    private String username;
    private String password;
    private String role;
    private String fio;
    private String address;
    private String phone;
    private Integer age;

    private String companyName;
    private String companyAddressDto;

}
