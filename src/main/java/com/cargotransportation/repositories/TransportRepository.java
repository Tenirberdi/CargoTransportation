package com.cargotransportation.repositories;

import com.cargotransportation.dao.Transport;
import com.cargotransportation.dto.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    @Query(value = "select * from transports where carrier_id = ?1", nativeQuery = true)
    List<Transport> getTransportByCarrierId(Long id);
}
