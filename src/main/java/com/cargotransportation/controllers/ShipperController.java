package com.cargotransportation.controllers;

import com.cargotransportation.constants.ResponseState;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
@RequestMapping("/shippers")
@AllArgsConstructor
public class ShipperController {

    private final OrderService orderService;

    @GetMapping("/order")
    public Response findAllByShipper(){
        return new Response(SUCCESS, 0,orderService.findAllByShipper());
    }

    @GetMapping("/order/{orderId}/confirm")
    public Response confirmOrderById(@PathVariable("orderId") Long orderId){
        return new Response(SUCCESS, 0, orderService.confirmByOrderId(orderId));
    }

    @GetMapping("/order/{orderId}/decline")
    public Response declineByOrderId(@PathVariable("orderId") Long orderId){
        return new Response(SUCCESS, 0, orderService.declineByOrderId(orderId));
    }
}