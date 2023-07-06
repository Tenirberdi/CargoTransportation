package com.cargotransportation.controllers;

import com.cargotransportation.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shippers")
@AllArgsConstructor
public class ShipperController {

    private final OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<?> findAllByCarrier(){
        return new ResponseEntity<>(orderService.findAllByCarrier(), HttpStatus.OK);
    }

}
