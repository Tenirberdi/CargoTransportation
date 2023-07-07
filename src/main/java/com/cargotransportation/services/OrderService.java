package com.cargotransportation.services;

import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateOrderRequest;
import com.cargotransportation.dto.response.OrderPriceInfoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderDto create(CreateOrderRequest request);
    OrderDto findById(Long id);

    OrderDto takeByOrderId(Long orderId);

    OrderDto confirmByOrderId(Long orderId);

    OrderDto declineByOrderId(Long orderId);

    OrderDto acceptByOrderId(Long orderId);
    OrderDto setProductTakenDateById(Long orderId);

    OrderDto setDeliveredDateById(Long orderId);

    OrderDto rejectById(Long orderId);

    List<OrderDto> findAllByStatus(OrderStatus status);

    List<OrderDto> findAllWithFilter(
            String sourceCity, String sourceState,
            String destinationCity, String destinationState,
            Integer minVolume, Integer maxVolume,
            String productTypeName,
            LocalDateTime minDate, LocalDateTime maxDate
    );

    OrderPriceInfoResponse getPriceInfoByOrderId(Long orderId);

    List<OrderDto> getAllConfirmedOrders();

    List<OrderDto> findAllByShipper();

    List<OrderDto> findAllByCarrierAndStatus(OrderStatus status);

}