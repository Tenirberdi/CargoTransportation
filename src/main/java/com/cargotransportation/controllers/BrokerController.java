package com.cargotransportation.controllers;

import com.cargotransportation.services.OrderService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brokers")
@AllArgsConstructor
public class BrokerController {

    private final OrderService orderService;

    @GetMapping("/order/{orderId}/accept")
    public ResponseEntity<?> acceptOrderById(
            @PathVariable("orderId") Long orderId)
    {
        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(orderService.acceptByOrderId(orderId),HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}/reject")
    public ResponseEntity<?> rejectOrderById(@PathVariable("orderId") Long orderId){
        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderService.rejectById(orderId),HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<?> getAllConfirmedOrders(){
        return new ResponseEntity<>(orderService.getAllConfirmedOrders(),HttpStatus.OK);
    }

}