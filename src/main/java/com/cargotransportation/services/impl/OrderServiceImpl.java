package com.cargotransportation.services.impl;

import com.cargotransportation.constants.AddressType;
import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.*;
import com.cargotransportation.dto.DocumentDto;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateAddressRequest;
import com.cargotransportation.dto.requests.CreateOrderRequest;
import com.cargotransportation.exception.IllegalStatusException;
import com.cargotransportation.exception.IsExistsException;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.OrderRepository;
import com.cargotransportation.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final ProductTypeService productTypeService;
    private final DocumentService documentService;
    private final UserService userService;


    @Override
    public OrderDto create(CreateOrderRequest request) {
        Address sourceAddress = Converter.convert(addressService.save(
                CreateAddressRequest.builder()
                        .address(request.getSourceAddress())
                        .state(request.getSourceState())
                        .coordinates(request.getSourceCoordinates())
                        .city(request.getSourceCity())
                        .build()));
        log.info("Source address created: " + sourceAddress);

        Address destinationAddress = Converter.convert(addressService.save(
                CreateAddressRequest.builder()
                        .address(request.getDestinationAddress())
                        .state(request.getDestinationState())
                        .city(request.getDestinationCity())
                        .coordinates(request.getDestinationCoordinates())
                        .build()));

        ProductType productType = Converter.convert(productTypeService.findByName(request.getProductType()));
        log.info("Product type found: " + productType);

        Order order = Order.builder()
                .shipper(Converter.convert(userService.findUserByRoleAndId("SHIPPER",request.getShipperId())))
                .sourceAddress(sourceAddress)
                .carrier(null)
                .broker(null)
                .currentLocation(null)
                .status(OrderStatus.WAITING)
                .createdDate(LocalDateTime.now())
                .destinationAddress(destinationAddress)
                .volume(request.getVolume())
                .productType(productType)
                .price(request.getPrice())
                .estimatedDeliveryDate(request.getEstimatedDeliveryDate())
                .build();
        orderRepository.saveAndFlush(order);

        OrderDto orderDto = Converter.convert(order);
        return Converter.convert(order);
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundException(
                "Order with id '" + id + "' not found!",
                HttpStatus.NOT_FOUND
        ));
        return Converter.convert(order);
    }

    @Override
    public OrderDto takeByOrderIdAndCarrierId(Long orderId, Long carrierId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!",
                HttpStatus.NOT_FOUND
        ));

        isOrderRejected(order);

        if(order.getCarrier() != null) throw new IsExistsException(
                "Order with id '" + orderId + "' has already been taken",
                HttpStatus.BAD_REQUEST
                );

        User carrier = Converter.convert(userService.findUserByRoleAndId("CARRIER",carrierId));
        order.setCarrier(carrier);

        order.setStatus(OrderStatus.TAKEN);

        return Converter.convert(orderRepository.save(order));
    }

    @Override
    public OrderDto acceptByOrderIdAndBrokerId(Long orderId,Long brokerId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!",
                HttpStatus.NOT_FOUND
        ));
        User user = Converter.convert(userService.findUserByRoleAndId("BROKER",brokerId));

        isOrderRejected(order);

        if(order.getStatus() != OrderStatus.TAKEN)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not took!",
                    HttpStatus.CONFLICT);
        }
        order.setStatus(OrderStatus.ACCEPTED);
        order.setBroker(user);
        return Converter.convert(orderRepository.save(order));
    }

    @Override
    public OrderDto setProductTakenDateById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!",
                HttpStatus.NOT_FOUND
        ));
        isOrderRejected(order);
        if(order.getStatus() != OrderStatus.ACCEPTED)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not accepted!",
                    HttpStatus.CONFLICT);
        }
        order.setTakenDate(LocalDateTime.now());
        order.setStatus(OrderStatus.SHIPPED);

        return Converter.convert(orderRepository.save(order));
    }

    @Override
    public OrderDto setDeliveredDateById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!",
                HttpStatus.NOT_FOUND
        ));
        isOrderRejected(order);
        if(order.getStatus() != OrderStatus.SHIPPED)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not shipped!",
                    HttpStatus.CONFLICT);
        }
        order.setDeliveredDate(LocalDateTime.now());
        order.setStatus(OrderStatus.DELIVERED);

        return Converter.convert(orderRepository.save(order));
    }

    @Override
    public OrderDto rejectById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!",
                HttpStatus.NOT_FOUND
        ));
        if(order.getStatus() != OrderStatus.TAKEN) {
            throw new IllegalStatusException(
                    "Order has already been accepted!",
                    HttpStatus.CONFLICT
            );
        }
        order.setStatus(OrderStatus.REJECTED);

        return Converter.convert(orderRepository.save(order));
    }


    @Override
    public List<OrderDto> findAllByStatus(OrderStatus status) {
        return orderRepository.findAll().stream().map(Converter::convert).collect(Collectors.toList());
    }



    @Override
    public List<OrderDto> findAllWithFilter(
            Long minPrice, Long maxPrice,
            String sourceCity, String sourceState, String destinationCity, String destinationState,
            Integer minVolume, Integer maxVolume,
            String productTypeName,
            LocalDateTime minDate, LocalDateTime maxDate
    ) {
        ProductType productType = productTypeName==null?null:Converter.convert(productTypeService.findByName(productTypeName));
        return orderRepository.filterOrders(
                minPrice, maxPrice,
                        sourceCity, sourceState,
                        destinationCity,destinationState,
                        minVolume, maxVolume,
                        productType,
                        minDate, maxDate
                )
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());

    }

    private void isOrderRejected(Order o){
        if(o.getStatus() == OrderStatus.REJECTED){
            throw new IllegalStatusException(
                    "Order with id '" + o.getId() + " is rejected!",
                    HttpStatus.CONFLICT
            );
        }
    }

}