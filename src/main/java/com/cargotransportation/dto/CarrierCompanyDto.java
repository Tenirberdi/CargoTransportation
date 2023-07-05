package com.cargotransportation.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrierCompanyDto extends UserDto {
    private String companyName;
    private String companyAddressDto;
    private int pricePerLb;
    private int pricePerKm;
    private int percentToExpress;
    private int percentToStandard;
    private List<TransportDto> companyTransports = new ArrayList<>();
}
