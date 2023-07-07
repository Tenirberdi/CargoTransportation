package com.cargotransportation.controllers;

import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.dto.requests.UpdateAddressRequest;
import com.cargotransportation.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carriers")
@AllArgsConstructor
public class CarrierController {

    private final OrderService orderService;

    @PutMapping("/current-location/{orderId}/update")
    public ResponseEntity<?> updateCurrentLocation(@PathVariable("orderId") Long orderId,@RequestBody UpdateAddressRequest request){
        return new ResponseEntity<>(orderService.updateCurrentLocationByOrderId(orderId,request),HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}/take")
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

    @GetMapping("/order/{status}")
    public ResponseEntity<?> getAllByStatus(@PathVariable("status")String status){
        return new ResponseEntity<>(orderService.findAllByCarrierAndStatus(OrderStatus.valueOf(status)),HttpStatus.OK);
    }

}
