package com.cargotransportation.converter;

import com.cargotransportation.dao.*;
import com.cargotransportation.dto.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Converter {

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSSSS";

    public static Role convert(RoleDto dto){
        if(dto == null)  return null;
        return Role.builder()
                .name(dto.getName())
                .build();
    }

    public static RoleDto convert(Role entity){
        if(entity == null)  return null;
        return RoleDto.builder()
                .name(entity.getName())
                .build();
    }

    public static Transport convert(TransportDto dto){
        if(dto == null)  return null;
        return Transport.builder()
                .id(dto.getId())
                .model(dto.getModel())
                .number(dto.getNumber())
                .capacity(dto.getCapacity())
                .type(dto.getType())
                .carrier(Converter.convert(dto.getCarrier()))
                .build();
    }

    public static TransportDto convert(Transport entity){
        if(entity == null)  return null;
        return TransportDto.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .number(entity.getNumber())
                .capacity(entity.getCapacity())
                .type(entity.getType())
                .carrier(Converter.convert(entity.getCarrier()))
                .build();
    }

    public static ProductType convert(ProductTypeDto dto){
        if(dto == null)  return null;
        return ProductType.builder()
                .name(dto.getName())
                .build();
    }

    public static ProductTypeDto convert(ProductType entity){
        if(entity == null)  return null;
        return ProductTypeDto.builder()
                .name(entity.getName())
                .build();
    }

    public static Address convert(AddressDto dto){
        if(dto == null)  return null;
        return Address.builder()
                .id(dto.getId())
                .address(dto.getAddress())
                .state(dto.getState())
                .coordinates(dto.getCoordinates())
                .type(dto.getType())
                .city(dto.getCity())
                .build();
    }

    public static AddressDto convert(Address entity){
        if(entity == null)  return null;
        return AddressDto.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .state(entity.getState())
                .coordinates(entity.getCoordinates())
                .type(entity.getType())
                .city(entity.getCity())
                .build();
    }

    public static Document convert(DocumentDto dto){
        if(dto == null)  return null;
        return Document.builder()
                .id(dto.getId())
                .order(Converter.convert(dto.getOrder()))
                .format(dto.getFormat())
                .type(dto.getType())
                .location(dto.getLocation())
                .build();
    }

    public static DocumentDto convert(Document entity){
        if(entity == null)  return null;
        return DocumentDto.builder()
                .id(entity.getId())
                .order(Converter.convert(entity.getOrder()))
                .format(entity.getFormat())
                .type(entity.getType())
                .location(entity.getLocation())
                .build();
    }

    public static User convert(UserDto dto){
        if(dto == null)  return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(dto.getRole())
                .createdAt(dto.getCreatedAt())
                .isConfirmed(dto.isConfirmed())
                .documents(dto.getDocuments().stream().map(Converter::convert).collect(Collectors.toList()))
                .build();
    }

    public static UserDto convert(User entity){
        if(entity == null)  return null;
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .isConfirmed(entity.isConfirmed())
                .documents(entity.getDocuments().stream().map(Converter::convert).collect(Collectors.toList()))
                .build();
    }


    public static Order convert(OrderDto dto) {
        if(dto == null)  return null;
        return Order.builder()
                .id(dto.getId())
                .carrier(Converter.convert(dto.getCarrier()))
                .shipper(Converter.convert(dto.getShipper()))
                .broker(Converter.convert(dto.getBroker()))
                .destinationAddress(Converter.convert(dto.getDestinationAddress()))
                .sourceAddress(Converter.convert(dto.getSourceAddress()))
                .volume(dto.getVolume())
                .price(dto.getPrice())
                .productType(Converter.convert(dto.getProductType()))
                .createdDate(dto.getCreatedDate())
                .takenDate(dto.getTakenDate())
                .deliveredDate(dto.getDeliveredDate())
                .estimatedDeliveryDate(dto.getEstimatedDeliveryDate())
                .status(dto.getStatus())
                .currentLocation(Converter.convert(dto.getCurrentLocation()))
                .build();
    }

    public static OrderDto convert(Order entity) {
        if(entity == null)  return null;
        return OrderDto.builder()
                .id(entity.getId())
                .carrier(Converter.convert(entity.getCarrier()))
                .shipper(Converter.convert(entity.getShipper()))
                .broker(Converter.convert(entity.getBroker()))
                .destinationAddress(Converter.convert(entity.getDestinationAddress()))
                .sourceAddress(Converter.convert(entity.getSourceAddress()))
                .volume(entity.getVolume())
                .price(entity.getPrice())
                .productType(Converter.convert(entity.getProductType()))
                .createdDate(entity.getCreatedDate())
                .takenDate(entity.getTakenDate())
                .deliveredDate(entity.getDeliveredDate())
                .estimatedDeliveryDate(entity.getEstimatedDeliveryDate())
                .status(entity.getStatus())
                .currentLocation(Converter.convert(entity.getCurrentLocation()))
                .build();
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateTime){
        if(dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }

}
