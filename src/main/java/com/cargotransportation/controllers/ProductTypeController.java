package com.cargotransportation.controllers;

import com.cargotransportation.services.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-types")
@AllArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(productTypeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam("name") String name){
        return new ResponseEntity<>(productTypeService.save(name),HttpStatus.OK);
    }



}
