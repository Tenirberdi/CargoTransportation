package com.cargotransportation.services;

import com.cargotransportation.constants.DocumentType;
import com.cargotransportation.dto.DocumentDto;
import com.cargotransportation.dto.FileInfoDto;
import com.cargotransportation.dto.requests.CreateDocumentRequest;

import java.util.List;

public interface DocumentService {
    DocumentDto save(DocumentDto documentDto);
    List<FileInfoDto> getUserFiles(Long userId);
    List<FileInfoDto> getOrderFiles(Long orderId);
    List<DocumentType> getDocumentTypes();
}
