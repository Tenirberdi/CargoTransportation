package com.cargotransportation.dto.requests;

import com.cargotransportation.constants.DocumentType;
import com.cargotransportation.dao.OrderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CreateOrderRequest {
    private Long shipperId;

    private String sourceCity;
    private String sourceState;
    private String sourceAddress;
    private Double sourceLatitude;
    private Double sourceLongitude;

    private String destinationCity;
    private String destinationState;
    private String destinationAddress;
    private Double destinationLongitude;
    private Double destinationLatitude;

    private Integer volume;
    private String productType;
    private Long price;
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;

    private LocalDateTime estimatedDeliveryDate;
}
