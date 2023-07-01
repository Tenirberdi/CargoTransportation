package com.cargotransportation.converter;

import com.cargotransportation.dao.*;
import com.cargotransportation.dto.*;
import org.aspectj.weaver.ast.Or;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
                .capacity(dto.getCapacity())
                .type(dto.getType())
                .carrier(Converter.convert(dto.getCarrier()))
                .build();
    }

    public static TransportDto convert(Transport entity){
        return TransportDto.builder()
                .model(entity.getModel())
                .number(entity.getNumber())
                .capacity(entity.getCapacity())
                .type(entity.getType())
                .carrier(Converter.convert(entity.getCarrier()))
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

    public static Document convert(DocumentDto dto){
        return Document.builder()
                .order(Converter.convert(dto.getOrder()))
                .format(dto.getFormat())
                .type(dto.getType())
                .location(dto.getLocation())
                .build();
    }

    public static DocumentDto convert(Document entity){
        return DocumentDto.builder()
                .order(Converter.convert(entity.getOrder()))
                .format(entity.getFormat())
                .type(entity.getType())
                .location(entity.getLocation())
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
                .password(dto.getPassword())
                .role(dto.getRole())
                .createdAt(dto.getCreatedAt())
                .isConfirmed(dto.isConfirmed())
                .documents(dto.getDocuments().stream().map(Converter::convert).collect(Collectors.toList()))
                .build();
    }

    public static UserDto convert(User entity){
        return UserDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .isConfirmed(entity.isConfirmed())
                .documents(entity.getDocuments().stream().map(Converter::convert).collect(Collectors.toList()))
                .build();
    }

    public static Carrier convert(CarrierDto dto){
        return Carrier.builder()
                .user(Converter.convert(dto.getUser()))
                .followedUser(Converter.convert(dto.getFollowedUser()))
                .followingUser(Converter.convert(dto.getFollowingUser()))
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public static CarrierDto convert(Carrier entity){
        return CarrierDto.builder()
                .user(Converter.convert(entity.getUser()))
                .followedUser(Converter.convert(entity.getFollowedUser()))
                .followingUser(Converter.convert(entity.getFollowingUser()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static Shipper convert(ShipperDto dto){
        return Shipper.builder()
                .user(Converter.convert(dto.getUser()))
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public static ShipperDto convert(Shipper entity){
        return ShipperDto.builder()
                .user(Converter.convert(entity.getUser()))
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static BrokerDto convert(Broker entity){
        return BrokerDto.builder()
                .user(Converter.convert(entity.getUser()))
                .build();
    }
    public static Broker convert(BrokerDto dto){
        return Broker.builder()
                .user(Converter.convert(dto.getUser()))
                .build();
    }

    public static Order convert(OrderDto dto) {
        return Order.builder()
                .carrier(Converter.convert(dto.getCarrier()))
                .shipper(Converter.convert(dto.getShipper()))
                .broker(Converter.convert(dto.getBroker()))
                .destinationAddress(Converter.convert(dto.getDestinationAddress()))
                .sourceAddress(Converter.convert(dto.getSourceAddress()))
                .volume(dto.getVolume())
                .productType(Converter.convert(dto.getProductType()))
                .documents(dto.getDocuments().stream().map(Converter::convert).collect(Collectors.toList()))
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
                .carrier(Converter.convert(entity.getCarrier()))
                .shipper(Converter.convert(entity.getShipper()))
                .broker(Converter.convert(entity.getBroker()))
                .destinationAddress(Converter.convert(entity.getDestinationAddress()))
                .sourceAddress(Converter.convert(entity.getSourceAddress()))
                .volume(entity.getVolume())
                .productType(Converter.convert(entity.getProductType()))
                .documents(entity.getDocuments().stream().map(Converter::convert).collect(Collectors.toList()))
                .createdDate(entity.getCreatedDate())
                .takenDate(entity.getTakenDate())
                .deliveredDate(entity.getDeliveredDate())
                .estimatedDeliveryDate(entity.getEstimatedDeliveryDate())
                .status(entity.getStatus())
                .currentLocation(Converter.convert(entity.getCurrentLocation()))
                .build();
    }

}
