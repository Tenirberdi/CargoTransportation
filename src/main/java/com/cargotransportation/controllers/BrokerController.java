package com.cargotransportation.controllers;

import com.cargotransportation.constants.UserStatus;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.services.OrderService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
@RequestMapping("/brokers")
@AllArgsConstructor
public class BrokerController {

    private final OrderService orderService;
    private final UserService userService;

    @PutMapping("/order/{orderId}/accept")
    public Response acceptOrderById(
            @PathVariable("orderId") Long orderId)
    {
//        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new Response(SUCCESS, 0, orderService.acceptByOrderId(orderId));
    }

    @PutMapping("/order/{orderId}/reject")
    public Response rejectOrderById(@PathVariable("orderId") Long orderId){
//        if(orderId == null || orderId < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new Response(SUCCESS, 0, orderService.rejectById(orderId));
    }

    @GetMapping("/order")
    public Response getAllConfirmedOrders(){
        return new Response(SUCCESS, 0, orderService.getAllConfirmedOrders());
    }

    @GetMapping("/users")
    public Response getUsers(){
        return new Response(SUCCESS, 0, userService.getUsers());
    }

    @PutMapping("/users/{id}/{status}")
    public Response changeUserConfirmStatus(@PathVariable Long id, @PathVariable UserStatus status) {
        userService.changeUserConfirmedStatus(id, status);
        return new Response(SUCCESS, 0);
    }



}