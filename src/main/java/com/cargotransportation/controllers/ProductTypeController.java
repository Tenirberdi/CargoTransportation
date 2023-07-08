package com.cargotransportation.controllers;

import com.cargotransportation.constants.ResponseState;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.services.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
@RequestMapping("/product-types")
@AllArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping
    public Response getAll(){
        return new Response(SUCCESS, 0,
                productTypeService.findAll());
    }

    @PostMapping
    public Response save(@RequestParam("name") String name){
        return new Response(SUCCESS, 0,
                productTypeService.save(name));
    }



}
