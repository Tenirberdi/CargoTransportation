package com.cargotransportation.dto;

import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.dao.Broker;
import com.cargotransportation.dao.Carrier;
import com.cargotransportation.dao.Shipper;
import com.cargotransportation.dto.response.UserResponse;
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
public class OrderDto {
    private ShipperDto shipper;
    private BrokerDto broker;
    private CarrierDto carrier;
    private AddressDto sourceAddress;
    private AddressDto destinationAddress;
    private Integer volume;
    private ProductTypeDto productType;
    private List<DocumentDto> documents;
    private LocalDateTime createdDate;
    private LocalDateTime takenDate;
    private LocalDateTime deliveredDate;
    private LocalDateTime estimatedDeliveryDate;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private AddressDto currentLocation;
}
