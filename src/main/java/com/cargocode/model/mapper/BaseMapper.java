package com.cargocode.model.mapper;


import java.util.List;

public interface BaseMapper <ENTITY, DTO>{
    DTO toDto(ENTITY entity);
    ENTITY toEntity(DTO dto);
    List<DTO> toDtoList(List<ENTITY> entities);
}
