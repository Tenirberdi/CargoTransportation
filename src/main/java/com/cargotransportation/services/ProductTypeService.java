package com.cargotransportation.services;

import com.cargotransportation.dao.ProductType;
import com.cargotransportation.dto.ProductTypeDto;

import java.util.List;

public interface ProductTypeService {

    ProductTypeDto findByName(String name);

    List<ProductTypeDto> findAll();

    ProductTypeDto save(String name);

}
