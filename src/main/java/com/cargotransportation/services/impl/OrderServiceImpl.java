package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Address;
import com.cargotransportation.dao.Order;
import com.cargotransportation.dao.ProductType;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateAddressRequest;
import com.cargotransportation.dto.requests.CreateOrderRequest;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.OrderRepository;
import com.cargotransportation.services.AddressService;
import com.cargotransportation.services.DocumentService;
import com.cargotransportation.services.OrderService;
import com.cargotransportation.services.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
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
//                .shipper(Converter.convert(shipperService.findById(request.getShipperId())))
                .sourceAddress(sourceAddress)
                .destinationAddress(destinationAddress)
                .volume(request.getVolume())
                .productType(productType)
                .estimatedDeliveryDate(request.getEstimatedDeliveryDate())
                .build();
        orderRepository.saveAndFlush(order);

        return Converter.convert(order);
    }

    @Override
    @PreAuthorize("hasAuthority('order.read')")
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundException(
                "Order with id '" + id + "' not found!"
        ));
        return Converter.convert(order);
    }
}
