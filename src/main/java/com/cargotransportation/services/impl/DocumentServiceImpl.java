package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Document;
import com.cargotransportation.dao.Order;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.DocumentDto;
import com.cargotransportation.dto.requests.CreateDocumentRequest;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.DocumentRepository;
import com.cargotransportation.services.DocumentService;
import com.cargotransportation.services.OrderService;
import com.cargotransportation.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;
    private final OrderService orderService;

    public DocumentServiceImpl(DocumentRepository documentRepository, UserService userService, @Lazy OrderService orderService) {
        this.documentRepository = documentRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public DocumentDto create(CreateDocumentRequest request,Long orderId,Long userId) {

        User user = userId==null?null:Converter.convert(userService.findById(userId));
        Order order = orderId==null?null:Converter.convert(orderService.findById(orderId));

        if(user == null && order == null){
            throw new NotFoundException("User and order is null", HttpStatus.BAD_REQUEST);
        }
        Document document = Document.builder()
                .location(request.getLocation())
                .type(request.getType())
                .format(request.getFormat())
                .order(order)
                .user(user)
                .build();
        log.info("Document created: " + document);
        return Converter.convert(
                documentRepository.save(document));
    }

    @Override
    public List<DocumentDto> saveAll(List<CreateDocumentRequest> requests,Long orderId,Long userId) {
        List<DocumentDto> documents = new ArrayList<>();
        for(CreateDocumentRequest request:requests){
            documents.add(create(request,orderId,userId));
        }
        log.info("Documents created: " + documents);
        return documents;
    }

}
