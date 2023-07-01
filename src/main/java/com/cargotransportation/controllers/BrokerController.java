package com.cargotransportation.controllers;

import com.cargotransportation.services.OrderService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brokers")
@AllArgsConstructor
public class BrokerController {

    private final OrderService orderService;

    @PutMapping("/order/{orderId}/accept/{brokerId}")
    public ResponseEntity<?> acceptOrderById(@PathVariable("orderId") Long orderId,@PathVariable("brokerId") Long brokerId){
        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(orderService.acceptByOrderIdAndBrokerId(orderId,brokerId),HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/reject/{brokerId}")
    public ResponseEntity<?> setOrderDeliveredDate(@PathVariable("orderId") Long orderId){
        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderService.rejectById(orderId),HttpStatus.OK);
    }

}
