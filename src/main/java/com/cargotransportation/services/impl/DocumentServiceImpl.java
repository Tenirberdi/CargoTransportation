package com.cargotransportation.services.impl;

import com.cargotransportation.constants.DocumentType;
import com.cargotransportation.dao.Document;
import com.cargotransportation.dto.DocumentDto;
import com.cargotransportation.dto.FileInfoDto;
import com.cargotransportation.dto.requests.CreateDocumentRequest;
import com.cargotransportation.exception.FileLoadException;
import com.cargotransportation.repositories.DocumentRepository;
import com.cargotransportation.repositories.OrderRepository;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.DocumentService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public DocumentDto save(DocumentDto documentDto) {
        if(documentDto.getUserId() == null && documentDto.getOrderId() == null ||
                documentDto.getUserId() != null && documentDto.getOrderId() != null) {
            throw new FileLoadException("Only one of userId and orderId must be not null");
        }

        documentRepository.save(Document.builder()
                .format(documentDto.getFormat())
                .type(documentDto.getType())
                .name(documentDto.getName())
                .order(documentDto.getOrderId() == null?
                        null:orderRepository.findById(documentDto.getOrderId()).get())
                .user(documentDto.getUserId() == null?
                        null:userRepository.findById(documentDto.getUserId()).get()).build());
        return documentDto;
    }

    @Override
//    @PreAuthorize("hasAuthority('file.read')")
    public List<FileInfoDto> getUserFiles(Long userId) {
        return documentRepository.getUsersFiles(userId).stream().map(fileInfo -> FileInfoDto.builder()
                .name(fileInfo.getName())
                .documentType(fileInfo.getType()).build()).collect(Collectors.toList());
    }

    @Override
//    @PreAuthorize("hasAuthority('file.read')")
    public List<FileInfoDto> getOrderFiles(Long orderId) {
        return documentRepository.getOrderFiles(orderId).stream().map(fileInfo -> FileInfoDto.builder()
                .name(fileInfo.getName())
                .documentType(fileInfo.getType()).build()).collect(Collectors.toList());
    }

    @Override
    public List<DocumentType> getDocumentTypes() {
        return Arrays.asList(DocumentType.values());
    }

}
