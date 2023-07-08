package com.cargotransportation.services;

import com.cargotransportation.dto.AgentDto;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.dto.requests.UpdateAgentRequest;

import java.util.List;

public interface AgentService {
    List<AgentDto> getAll();
    AgentDto findById(Long id);
    AgentDto createTransports(List<CreateTransportRequest> requests);
    AgentDto findByUsername(String username);

    AgentDto updatePrices(UpdateAgentRequest request);

}
