package com.cargocode.service.impl;

import com.cargocode.exception.document.DocumentNotFound;
import com.cargocode.model.dto.DocumentDto;
import com.cargocode.model.entity.Document;
import com.cargocode.model.mapper.DocumentMapper;
import com.cargocode.repository.DocumentRepository;
import com.cargocode.service.DocumentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentServiceImpl implements DocumentService {

    DocumentRepository documentRepository;

    @Override
    public List<DocumentDto> getDocumentsByUserId(Long id) {
        return DocumentMapper.INSTANCE.toDtoList(documentRepository.findAllDocumentByUserId(id));
    }

    @Override
    public DocumentDto getDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() ->
                        new DocumentNotFound("Документ с ID '" + id + "' не был найден!", HttpStatus.NOT_FOUND));

        return DocumentMapper.INSTANCE.toDto(document);
    }

    @Override
    public DocumentDto addDocumentToUserById(Long id) {
        return null;
    }

}
