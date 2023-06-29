package com.cargocode.service;


import com.cargocode.model.dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    List<DocumentDto> getDocumentsByUserId(Long id);
    DocumentDto getDocumentById(Long id);

    DocumentDto addDocumentToUserById(Long id);
}
