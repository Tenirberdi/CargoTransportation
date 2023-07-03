package com.cargotransportation.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrierCompanyDto extends UserDto {
    private String companyName;
    private AddressDto companyAddressDto;
    private int pricePerLb;
    private int pricePerKm;
    private List<TransportDto> companyTransports;
}
