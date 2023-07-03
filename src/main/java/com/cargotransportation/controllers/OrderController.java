package com.cargotransportation.controllers;

import com.cargotransportation.constants.AddressType;
import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.converter.Converter;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateOrderRequest;
import com.cargotransportation.services.OrderService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateOrderRequest request){
        return new ResponseEntity<>(orderService.create(request), HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> findAllByStatus(@PathVariable("status") String status){
        return new ResponseEntity<>(orderService.findAllByStatus(OrderStatus.valueOf(status)),HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findAllWithFilter(
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
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
                minPrice, maxPrice,
                sourceCity, sourceState, destinationCity, destinationState,
                minVolume, maxVolume,
                productTypeName,
                Converter.convertStringToLocalDateTime(minDate), Converter.convertStringToLocalDateTime(maxDate)
        );
        return new ResponseEntity<>(filteredOrders, HttpStatus.OK);
    }


}
