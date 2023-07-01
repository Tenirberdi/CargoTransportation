package com.cargotransportation.dto;

import com.cargotransportation.constants.DocumentType;
import com.cargotransportation.dao.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private String format;

    @Enumerated(value = EnumType.STRING)
    private DocumentType type;

    private String location;

    private OrderDto order;

    private UserDto user;

//    private CarrierDto carrier;
//
//    private ShipperDto shipper;
}
