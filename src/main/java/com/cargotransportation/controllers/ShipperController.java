package com.cargotransportation.controllers;

import com.cargotransportation.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shippers")
@AllArgsConstructor
public class ShipperController {

    private final OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<?> findAllByShipper(){
        return new ResponseEntity<>(orderService.findAllByShipper(), HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/confirm")
    public ResponseEntity<?> confirmOrderById(@PathVariable("orderId") Long orderId){
        return new ResponseEntity<>(orderService.confirmByOrderId(orderId),HttpStatus.OK);
    }

}
