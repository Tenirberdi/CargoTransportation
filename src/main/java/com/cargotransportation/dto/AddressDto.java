package com.cargotransportation.dto;

import com.cargotransportation.constants.AddressType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String city;

    private String state;

    private String address;
    private String coordinates;
    @Enumerated(value = EnumType.STRING)
    private AddressType type;
}
