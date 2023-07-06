package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.ProductType;
import com.cargotransportation.dto.ProductTypeDto;
import com.cargotransportation.exception.IsExistsException;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.ProductTypeRepository;
import com.cargotransportation.services.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Override
    @PreAuthorize("hasAuthority('productType.read')")
    public ProductTypeDto findByName(String name) {
        ProductType productType = productTypeRepository.
                findByName(name);
        if(productType == null){
            throw new NotFoundException(
                    "Product type with name '" + name + "' not found!"
            );
        }
        return Converter.convert(productType);
    }

    @Override
    public List<ProductTypeDto> findAll() {
        return productTypeRepository.findAll()
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ProductTypeDto save(String name) {
        ProductType productType = productTypeRepository.
                findByName(name);
        if(productType == null){
            return Converter.convert(productTypeRepository.save(new ProductType(name)));
        }
        throw new IsExistsException(
                "Product type with name '" + name + "' is exists!",
                HttpStatus.CONFLICT
        );
    }
}
