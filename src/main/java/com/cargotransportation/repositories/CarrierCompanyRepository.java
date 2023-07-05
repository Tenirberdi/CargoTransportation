package com.cargotransportation.repositories;

import com.cargotransportation.dao.CarrierCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierCompanyRepository extends JpaRepository<CarrierCompany,Long> {
}
