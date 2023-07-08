package com.cargotransportation.repositories;

import com.cargotransportation.dao.Agent;
import com.cargotransportation.dao.Transport;
import com.cargotransportation.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    Transport findByCarrier(User carrier);
    List<Transport> findAllByAgent_Id(Long agentId);

    List<Transport> findByCarrierIsNullAndAgent(Agent agent);



}
