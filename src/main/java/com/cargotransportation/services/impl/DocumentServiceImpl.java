package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Document;
import com.cargotransportation.dao.Order;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.DocumentDto;
import com.cargotransportation.dto.requests.CreateDocumentRequest;
import com.cargotransportation.exception.document.DocumentException;
import com.cargotransportation.repositories.DocumentRepository;
import com.cargotransportation.services.DocumentService;
import com.cargotransportation.services.OrderService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;
    private final OrderService orderService;

    @Override
    public DocumentDto create(CreateDocumentRequest request,Long orderId,Long userId) {

        User user = Converter.convert(userService.findById(userId));
        Order order = Converter.convert(orderService.findById(orderId));

        if(user == null && order == null){
            throw new DocumentException("User and order is null", HttpStatus.BAD_REQUEST);
        }

        return Converter.convert(
                Document.builder()
                        .location(request.getLocation())
                        .type(request.getType())
                        .format(request.getFormat())
                        .order(order)
                        .user(user)
                        .build()
        );
    }

    @Override
    public List<DocumentDto> saveAll(List<CreateDocumentRequest> requests,Long orderId,Long userId) {
        List<DocumentDto> documents = new ArrayList<>();
        for(CreateDocumentRequest request:requests){
            documents.add(create(request,orderId,userId));
        }
        return documents;
    }

}
