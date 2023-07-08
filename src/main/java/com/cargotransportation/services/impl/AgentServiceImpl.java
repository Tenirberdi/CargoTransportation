package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Agent;
import com.cargotransportation.dao.Transport;
import com.cargotransportation.dto.AgentDto;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.dto.requests.UpdateAgentRequest;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.AgentRepository;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.AgentService;
import com.cargotransportation.services.TransportService;
import com.cargotransportation.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final TransportService transportService;
    private final UserService userService;
    private final AuthService authService;
    private final UserRepository userRepository;

    public AgentServiceImpl(UserRepository userRepository, UserService userService, AuthService authService, AgentRepository agentRepository, @Lazy TransportService transportService) {
        this.agentRepository = agentRepository;
        this.transportService = transportService;
        this.userService = userService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Override
    public List<AgentDto> getAll() {
        return agentRepository.findAll()
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AgentDto findByUsername(String username){
        Agent agent = agentRepository.findByUsername(username);
        if(agent == null){
            throw new NotFoundException("Carrier company with username '" + username + "' not found!");
        }
        return Converter.convert(agent);
    }


    @Override
    public AgentDto findById(Long id) {
        Agent agent = agentRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Carrier company with id '" + id + "' not found!"));
        List<Transport> transports = transportService.findAllByCarrierCompanyId(agent.getId())
                .stream()
                .map(dto ->
                        Transport.builder()
                                .id(dto.getId())
                                .model(dto.getModel())
                                .number(dto.getNumber())
                                .capacityInTons(dto.getCapacityInTons())
                                .type(dto.getType())
                                .carrier(Converter.convert(dto.getCarrier()))
                                .agent(agent)
                                .build()
                )
                .collect(Collectors.toList());
        agent.setAgentTransports(transports);
        return Converter.convert(agent);
    }

    private Transport convert(TransportDto dto){

        if(dto == null)  return null;
        return Transport.builder()
                .id(dto.getId())
                .model(dto.getModel())
                .number(dto.getNumber())
                .capacityInTons(dto.getCapacityInTons())
                .type(dto.getType())
                .carrier(Converter.convert(dto.getCarrier()))
                .agent(agentRepository.findById(dto.getAgent()).get())
                .build();
    }

    @Override
    public AgentDto createTransports(List<CreateTransportRequest> requests) {
        Agent agent = Converter.convert(findById(authService.getCurrentUser().getId()));
        List<Transport> transports = new ArrayList<>();
        for(CreateTransportRequest request:requests){
            transports.add(convert(transportService.saveById(agent.getId(),request)));
        }
        agent.setAgentTransports(transports);
        AgentDto agentDto = AgentDto.builder()
                .id(agent.getId())
                .username(agent.getUsername())
                .role(agent.getRole().getName())
                .createdAt(agent.getCreatedAt())
                .isConfirmed(agent.isConfirmed())
                .fio(agent.getFio())
                .birthDate(agent.getBirthDate())
                .address(agent.getAddress())
                .phone(agent.getPhone())
                .percentToExpress(agent.getPercentToExpress())
                .percentToStandard(agent.getPercentToStandard())
                .pricePerKm(agent.getPricePerKm())
                .pricePerLb(agent.getPricePerLb())
                .companyTransports(transports.stream().map(Converter::convert).collect(Collectors.toList()))
                .companyName(agent.getCompanyName())
                .companyAddressDto(agent.getCompanyAddress())
                .build();
        return agentDto;
    }

    @Override
    @Transactional
    public AgentDto updatePrices(UpdateAgentRequest request) {
        Agent agent = Converter.convert(findById(authService.getCurrentUser().getId()));
        List<Transport> transports = transportService.findAllByCarrierCompanyId(agent.getId())
                .stream()
                .map(dto ->
                        Transport.builder()
                                .id(dto.getId())
                                .model(dto.getModel())
                                .number(dto.getNumber())
                                .capacityInTons(dto.getCapacityInTons())
                                .type(dto.getType())
                                .carrier(Converter.convert(dto.getCarrier()))
                                .agent(agent)
                                .build()
                )
                .collect(Collectors.toList());
        agent.setAgentTransports(transports);
        String password = userRepository.findById(agent.getId()).get().getPassword();
        agent.setPricePerKm(request.getPricePerKm());
        agent.setPricePerLb(request.getPricePerLb());
        agent.setPercentToExpress(request.getPercentToExpress());
        agent.setPercentToStandard(request.getPercentToStandard());
        agent.setPassword(password);

        return Converter.convert(agentRepository.save(agent));
    }
}
