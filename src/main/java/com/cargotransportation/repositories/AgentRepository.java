package com.cargotransportation.repositories;

import com.cargotransportation.dao.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
    Agent findByUsername(String username);
}
