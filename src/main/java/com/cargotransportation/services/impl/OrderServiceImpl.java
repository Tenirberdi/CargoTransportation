package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Address;
import com.cargotransportation.dao.Document;
import com.cargotransportation.dao.Order;
import com.cargotransportation.dao.ProductType;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateAddressRequest;
import com.cargotransportation.dto.requests.CreateOrderRequest;
import com.cargotransportation.exception.order.OrderNotFound;
import com.cargotransportation.repositories.OrderRepository;
import com.cargotransportation.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CarrierService carrierService;
    private final AddressService addressService;
    private final ShipperService shipperService;
    private final ProductTypeService productTypeService;
    private final DocumentService documentService;


    @Override
    public OrderDto create(CreateOrderRequest request) {

        Address sourceAddress = Converter.convert(addressService.save(
                CreateAddressRequest.builder()
                        .address(request.getSourceAddress())
                        .state(request.getSourceState())
                        .city(request.getSourceCity())
                        .build()));

        Address destinationAddress = Converter.convert(addressService.save(
                CreateAddressRequest.builder()
                        .address(request.getDestinationAddress())
                        .state(request.getDestinationState())
                        .city(request.getDestinationCity())
                        .build()));

        ProductType productType = Converter.convert(productTypeService.findByName(request.getProductType()));

        Order order = Order.builder()
                .shipper(Converter.convert(shipperService.findById(request.getShipperId())))
                .sourceAddress(sourceAddress)
                .destinationAddress(destinationAddress)
                .volume(request.getVolume())
                .productType(productType)
                .estimatedDeliveryDate(request.getEstimatedDeliveryDate())
                .build();
        orderRepository.saveAndFlush(order);

        documentService.saveAll(request.getDocuments(),order.getId(),null);

        return Converter.convert(order);
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFound(
                "Order with id '" + id + "' not found!",
                HttpStatus.NOT_FOUND
        ));
        return Converter.convert(order);
    }
}
