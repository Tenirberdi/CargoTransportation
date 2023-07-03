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
                .capacityInTons(dto.getCapacityInTons())
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
                .capacityInTons(entity.getCapacityInTons())
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
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
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
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .type(entity.getType())
                .city(entity.getCity())
                .build();
    }

    public static DocumentDto convert(Document entity){
        if(entity == null)  return null;
        return DocumentDto.builder()
                .id(entity.getId())
                .orderId(entity.getOrder().getId())
                .name(entity.getName())
                .format(entity.getFormat())
                .type(entity.getType())
                .userId(entity.getUser().getId())
                .build();
    }

    public static User convert(UserDto dto){
        if(dto == null)  return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .role(Role.builder().name(dto.getRole()).build())
                .createdAt(dto.getCreatedAt())
                .isConfirmed(dto.isConfirmed())
                .fio(dto.getFio())
                .age(dto.getAge())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .build();
    }

    public static UserDto convert(User entity){
        if(entity == null)  return null;
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole().getName())
                .createdAt(entity.getCreatedAt())
                .isConfirmed(entity.isConfirmed())
                .fio(entity.getFio())
                .age(entity.getAge())
                .address(entity.getAddress())
                .phone(entity.getPhone())
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
