package com.cargotransportation.controllers;

import com.cargotransportation.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carriers")
@AllArgsConstructor
public class CarrierController {

    private final OrderService orderService;

    @PutMapping("/order/{orderId}/take")
    public ResponseEntity<?> takeOrder(
            @PathVariable("orderId") Long orderId
    ){
        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderService.takeByOrderId(orderId),HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/setTakenDate")
    public ResponseEntity<?> setOrderTakenDate(@PathVariable("orderId") Long orderId){
        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderService.setProductTakenDateById(orderId),HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/setDeliveredDate")
    public ResponseEntity<?> setOrderDeliveredDate(@PathVariable("orderId") Long orderId) {
        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderService.setDeliveredDateById(orderId), HttpStatus.OK);
    }
}
