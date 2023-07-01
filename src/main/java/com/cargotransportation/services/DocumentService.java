package com.cargotransportation.services;

import com.cargotransportation.dto.DocumentDto;
import com.cargotransportation.dto.requests.CreateDocumentRequest;

import java.util.List;

public interface DocumentService {
    DocumentDto create(CreateDocumentRequest request,Long orderId,Long userId);
    List<DocumentDto> saveAll(List<CreateDocumentRequest> requests,Long orderId,Long userId);
}
