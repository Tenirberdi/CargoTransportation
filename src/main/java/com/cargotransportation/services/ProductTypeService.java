package com.cargotransportation.services;

import com.cargotransportation.dao.ProductType;
import com.cargotransportation.dto.ProductTypeDto;

public interface ProductTypeService {

    ProductTypeDto findByName(String name);

}
