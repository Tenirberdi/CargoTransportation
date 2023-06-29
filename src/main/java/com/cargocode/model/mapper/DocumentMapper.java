package com.cargocode.model.mapper;

import com.cargocode.model.dto.DocumentDto;
import com.cargocode.model.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface DocumentMapper extends BaseMapper<Document, DocumentDto> {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(target = "userId", source = "user.id")
    DocumentDto toDto(Document document);

    default List<DocumentDto> toDtoList(List<Document> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
