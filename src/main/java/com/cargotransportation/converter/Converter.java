package com.cargotransportation.converter;

import com.cargotransportation.dao.*;
import com.cargotransportation.dto.*;

import java.util.stream.Collectors;

public class Converter {

    public static Role convert(RoleDto dto){
        return Role.builder()
                .name(dto.getName())
                .build();
    }

    public static RoleDto convert(Role entity){
        return RoleDto.builder()
                .name(entity.getName())
                .build();
    }

    public static Transport convert(TransportDto dto){
        return Transport.builder()
                .model(dto.getModel())
                .number(dto.getNumber())
                .capacityInTons(dto.getCapacityInTons())
                .type(dto.getType())
//                .carrier(Converter.convert())
                .build();
    }

    public static TransportDto convert(Transport entity){
        return TransportDto.builder()
                .model(entity.getModel())
                .number(entity.getNumber())
                .capacityInTons(entity.getCapacityInTons())
                .type(entity.getType())
//                .carrier(Converter.convert(entity.getCarrier()))
                .build();
    }

    public static ProductType convert(ProductTypeDto dto){
        return ProductType.builder()
                .name(dto.getName())
                .build();
    }

    public static ProductTypeDto convert(ProductType entity){
        return ProductTypeDto.builder()
                .name(entity.getName())
                .build();
    }

    public static Address convert(AddressDto dto){
        return Address.builder()
                .address(dto.getAddress())
                .state(dto.getState())
                .city(dto.getCity())
                .build();
    }

    public static AddressDto convert(Address entity){
        return AddressDto.builder()
                .address(entity.getAddress())
                .state(entity.getState())
                .city(entity.getCity())
                .build();
    }

//    public static List<Document> convertList(List<DocumentDto> dtos){
//        List<Document> documents = new ArrayList<>();
//
//        for(DocumentDto dto:dtos) documents.add(Converter.convert(dto));
//
//        return documents;
//    }
//
//    public static List<DocumentDto> convertList(List<Document> documents){
//        List<DocumentDto> dtos = new ArrayList<>();
//
//        for(Document dto:documents) dtos.add(Converter.convert(dto));
//
//        return dtos;
//    }

    public static User convert(UserDto dto){
        return User.builder()
                .username(dto.getUsername())
                .role(Role.builder().name(dto.getRole()).build())
                .createdAt(dto.getCreatedAt())
                .isConfirmed(dto.isConfirmed())
                .fio(dto.getFio())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();
    }

    public static UserDto convert(User entity){
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole().getName())
                .fio(entity.getFio())
                .createdAt(entity.getCreatedAt())
                .isConfirmed(entity.isConfirmed())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .age(entity.getAge()).build();
    }

    public static Order convert(OrderDto dto) {
        return Order.builder()
                .destinationAddress(Converter.convert(dto.getDestinationAddress()))
                .sourceAddress(Converter.convert(dto.getSourceAddress()))
                .volume(dto.getVolume())
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
        return OrderDto.builder()
                .carrierId(entity.getCarrier().getId())
                .shipperId(entity.getShipper().getId())
                .brokerId(entity.getBroker().getId())
                .destinationAddress(Converter.convert(entity.getDestinationAddress()))
                .sourceAddress(Converter.convert(entity.getSourceAddress()))
                .volume(entity.getVolume())
                .productType(Converter.convert(entity.getProductType()))
                .createdDate(entity.getCreatedDate())
                .takenDate(entity.getTakenDate())
                .deliveredDate(entity.getDeliveredDate())
                .estimatedDeliveryDate(entity.getEstimatedDeliveryDate())
                .status(entity.getStatus())
                .currentLocation(Converter.convert(entity.getCurrentLocation()))
                .build();
    }

}
