package com.cargocode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    @PostMapping("/{id}")
    public ResponseEntity<?> addDocumentToUserById(@PathVariable("id") Long id){
        if(id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return null;
    }

}
