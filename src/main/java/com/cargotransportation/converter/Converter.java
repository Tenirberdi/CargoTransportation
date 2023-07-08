package com.cargotransportation.converter;

import com.cargotransportation.dao.*;
import com.cargotransportation.dto.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
                .agent(entity.getAgent()==null?null:entity.getAgent().getId())
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
                .birthDate(dto.getBirthDate())
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
                .birthDate(entity.getBirthDate())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .build();
    }

    public static CarrierDto convert(Carrier entity){
        if(entity == null)  return null;
        return CarrierDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole().getName())
                .createdAt(entity.getCreatedAt())
                .isConfirmed(entity.isConfirmed())
                .fio(entity.getFio())
                .birthDate(entity.getBirthDate())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .agentId(entity.getAgent().getId())
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
                .orderType(dto.getOrderType())
                .price(dto.getPrice())
                .productType(Converter.convert(dto.getProductType()))
                .createdDate(dto.getCreatedDate())
                .takenDate(dto.getTakenDate())
                .duration(dto.getDuration())
                .totalKm(dto.getTotalKm())
                .totalPrice(dto.getTotalPrice()==null?0.0:dto.getTotalPrice())
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
                .orderType(entity.getOrderType())
                .productType(Converter.convert(entity.getProductType()))
                .createdDate(entity.getCreatedDate())
                .takenDate(entity.getTakenDate())
                .deliveredDate(entity.getDeliveredDate())
                .estimatedDeliveryDate(entity.getEstimatedDeliveryDate())
                .status(entity.getStatus())
                .totalKm(entity.getTotalKm())
                .totalPrice(entity.getTotalPrice()==null?0.0:entity.getTotalPrice())
                .duration(entity.getDuration())
                .currentLocation(Converter.convert(entity.getCurrentLocation()))
                .build();
    }

    public static Agent convert(AgentDto dto){
        if(dto == null) return null;
        return Agent.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .role(Role.builder().name(dto.getRole()).build())
                .createdAt(dto.getCreatedAt())
                .isConfirmed(dto.isConfirmed())
                .fio(dto.getFio())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .address(dto.getAddress())
                .companyAddress(dto.getCompanyAddressDto())
                .companyName(dto.getCompanyName())
                .percentToExpress(dto.getPercentToExpress())
                .percentToStandard(dto.getPercentToStandard())
                .pricePerKm(dto.getPricePerKm())
                .agentTransports(dtosToEntities(dto.getCompanyTransports()))
                .pricePerLb(dto.getPricePerLb())
                .build();
    }

    private static List<TransportDto> entitiesToDtos(List<Transport> entities){
        if(entities == null) return null;
        return entities.stream().map(Converter::convert).collect(Collectors.toList());
    }

    private static List<Transport> dtosToEntities(List<TransportDto> dtos){
        if(dtos == null) return null;
        return dtos.stream().map(Converter::convert).collect(Collectors.toList());
    }

    public static AgentDto convert(Agent entity){
        if(entity == null) return null;
        return AgentDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole().getName())
                .createdAt(entity.getCreatedAt())
                .isConfirmed(entity.isConfirmed())
                .fio(entity.getFio())
                .birthDate(entity.getBirthDate())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .percentToExpress(entity.getPercentToExpress())
                .percentToStandard(entity.getPercentToStandard())
                .pricePerKm(entity.getPricePerKm())
                .pricePerLb(entity.getPricePerLb())
                .companyTransports(entitiesToDtos(entity.getAgentTransports()))
                .companyName(entity.getCompanyName())
                .companyAddressDto(entity.getCompanyAddress())
                .build();
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateTime){
        if(dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }

}
