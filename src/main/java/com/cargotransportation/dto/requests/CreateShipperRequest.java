package com.cargotransportation.dto.requests;

import com.cargotransportation.constants.TransportType;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.UserDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShipperRequest {
    private Long userId;
    private String status;
    private LocalDateTime createdAt;

    private String model;
    private String number;
    private String capacity;
    @Enumerated(value = EnumType.STRING)
    private TransportType type;
}
