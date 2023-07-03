package com.cargotransportation.dto.requests;

import com.cargotransportation.constants.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {
    private String city;

    private String state;

    private String address;

    private String coordinates;

    private AddressType type;
}
