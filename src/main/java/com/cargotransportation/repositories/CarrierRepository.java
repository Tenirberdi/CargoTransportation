package com.cargotransportation.repositories;

import com.cargotransportation.dao.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier,Long> {
}
