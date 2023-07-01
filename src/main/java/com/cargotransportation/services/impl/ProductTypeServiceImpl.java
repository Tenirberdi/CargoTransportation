package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.ProductType;
import com.cargotransportation.dto.ProductTypeDto;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.ProductTypeRepository;
import com.cargotransportation.services.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Override
    public ProductTypeDto findByName(String name) {
        ProductType productType = productTypeRepository.
                findByName(name);
        if(productType == null){
            throw new NotFoundException(
                    "Product type with name '" + name + "' not found!",
                    HttpStatus.NOT_FOUND
            );
        }
        return Converter.convert(productType);
    }
}
