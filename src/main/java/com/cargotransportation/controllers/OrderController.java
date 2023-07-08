package com.cargotransportation.controllers;

import com.cargotransportation.constants.AddressType;
import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.constants.ResponseState;
import com.cargotransportation.converter.Converter;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateOrderRequest;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.services.OrderService;
import com.cargotransportation.services.ProductTypeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductTypeService productTypeService;

    @PostMapping
    public Response create(@RequestBody CreateOrderRequest request){
        return new Response(SUCCESS, 0,
                orderService.create(request));
    }

    @GetMapping("/{status}")
    public Response findAllByStatus(@PathVariable("status") String status){
        return new Response(SUCCESS, 0,
                orderService.findAllByStatus(OrderStatus.valueOf(status)));
    }

    @GetMapping("/filter")
    public Response findAllWithFilter(
            @RequestParam(required = false) String sourceCity,
            @RequestParam(required = false) String sourceState,
            @RequestParam(required = false) String destinationCity,
            @RequestParam(required = false) String destinationState,
            @RequestParam(required = false) Integer minVolume,
            @RequestParam(required = false) Integer maxVolume,
            @RequestParam(required = false) String productTypeName,
            @RequestParam(required = false) String minDate,
            @RequestParam(required = false) String maxDate
    ) {
        List<OrderDto> filteredOrders = orderService.findAllWithFilter(
                sourceCity, sourceState, destinationCity, destinationState,
                minVolume, maxVolume,
                productTypeName,
                Converter.convertStringToLocalDateTime(minDate), Converter.convertStringToLocalDateTime(maxDate)
        );
        return new Response(SUCCESS, 0, filteredOrders);
    }

    @GetMapping("/{orderId}/price-info")
    public Response getPriceInfoById(@PathVariable("orderId") Long orderId){
        return new Response(SUCCESS, 0, orderService.getPriceInfoByOrderId(orderId));
    }

}
