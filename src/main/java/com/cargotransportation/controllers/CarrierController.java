package com.cargotransportation.controllers;

import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.constants.ResponseState;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.UpdateAddressRequest;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
@RequestMapping("/carriers")
@AllArgsConstructor
public class CarrierController {

    private final OrderService orderService;

    @PutMapping("/current-location/{orderId}/update")
    public Response updateCurrentLocation(@PathVariable("orderId") Long orderId, @RequestBody UpdateAddressRequest request){
        return new Response(SUCCESS, 0,
                orderService.updateCurrentLocationByOrderId(orderId,request));
    }

    @GetMapping("/order/{orderId}/take")
    public Response takeOrder(@PathVariable("orderId") Long orderId){
//        if(orderId == null || orderId < 1) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new Response(SUCCESS, 0, orderService.takeByOrderId(orderId));
    }

    @PutMapping("/order/{orderId}/setTakenDate")
    public Response setOrderTakenDate(@PathVariable("orderId") Long orderId) {
//        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new Response(SUCCESS, 0, orderService.setProductTakenDateById(orderId));
    }

    @PutMapping("/order/{orderId}/setDeliveredDate")
    public Response setOrderDeliveredDate(@PathVariable("orderId") Long orderId) {
//        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new Response(SUCCESS, 0, orderService.setDeliveredDateById(orderId));
    }

    @GetMapping("/order/{status}")
    public Response getAllByStatus(@PathVariable("status")String status){
        return new Response(SUCCESS, 0,
                orderService.findAllByCarrierAndStatus(OrderStatus.valueOf(status)));
    }

}
