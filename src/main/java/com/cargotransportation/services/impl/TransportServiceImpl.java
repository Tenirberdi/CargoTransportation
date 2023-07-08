package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Agent;
import com.cargotransportation.dao.Transport;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.TransportRepository;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.AgentService;
import com.cargotransportation.services.TransportService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final AgentService agentService;
    private final UserService userService;
    private final AuthService authService;
    @Override
    public TransportDto saveById(Long carrierCompanyId, CreateTransportRequest request) {
        Agent agent = Converter.convert(agentService.findById(carrierCompanyId));

        Transport transport = Transport.builder()
                .capacityInTons(request.getCapacityInTons())
                .type(request.getType())
                .model(request.getModel())
                .number(request.getNumber())
                .agent(agent)
                .build();

        agent.getAgentTransports().add(transport);

        return Converter.convert(transportRepository.save(transport));
    }

    @Override
    public TransportDto setCarrierByTransportIdCarrierId(Long transportId, Long carrierId) {
        User carrier = Converter.convert(userService.findById(carrierId));
        if(!carrier.getRole().getName().equals("ROLE_CARRIER"))
        {
            throw new NotFoundException("User with id '" + carrierId + "' is not carrier!");
        }

        Transport transport = transportRepository.findById(transportId)
                .orElseThrow(() -> new NotFoundException("Transport with id '" + transportId + "' not found!"));

        transport.setCarrier(carrier);
        carrier.setTransport(transport);
        transportRepository.save(transport);
        return TransportDto.builder()
                .id(carrier.getId())
                .model(carrier.getTransport().getModel())
                .number(carrier.getTransport().getNumber())
                .capacityInTons(transport.getCapacityInTons())
                .type(carrier.getTransport().getType())
                .carrier(Converter.convert(carrier))
                .agent(transport.getAgent().getId())
                .build();
    }

    @Override
    public List<TransportDto> findAllByCarrierCompanyId(Long id) {
        return transportRepository
                .findAllByAgent_Id(id)
                .stream()
                .map(Converter::convert)
                .toList();
    }

    @Override
    public List<TransportDto> findByCarrierIsNullAndCarrierCompany() {

        Agent agent = Converter.convert(agentService.findById(authService.getCurrentUser().getId()));

        return transportRepository.findByCarrierIsNullAndAgent(agent)
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }




}
