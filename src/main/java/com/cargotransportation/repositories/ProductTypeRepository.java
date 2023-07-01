package com.cargotransportation.repositories;

import com.cargotransportation.dao.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {

    ProductType findByName(String name);

}
