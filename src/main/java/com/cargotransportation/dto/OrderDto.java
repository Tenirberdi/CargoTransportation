package com.cargotransportation.dto;

import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.dao.OrderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private UserDto shipper;
    private UserDto broker;
    private UserDto carrier;
    private AddressDto sourceAddress;
    private AddressDto destinationAddress;
    private Integer volume;
    private Long price;
    private ProductTypeDto productType;
    private LocalDateTime createdDate;
    private LocalDateTime takenDate;
    private LocalDateTime deliveredDate;
    private LocalDateTime estimatedDeliveryDate;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private AddressDto currentLocation;
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;
}
