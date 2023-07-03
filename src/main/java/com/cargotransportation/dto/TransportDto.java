package com.cargotransportation.dto;

import com.cargotransportation.constants.TransportType;
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
public class TransportDto {
    private Long id;
    private String model;
    private String number;
    private Integer capacityInTons;
    @Enumerated(value = EnumType.STRING)
    private TransportType type;
    private UserDto carrier;
}
