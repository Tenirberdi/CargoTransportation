package com.cargotransportation.services;

import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateOrderRequest;

public interface OrderService {
    OrderDto create(CreateOrderRequest request);
    OrderDto findById(Long id);
}
