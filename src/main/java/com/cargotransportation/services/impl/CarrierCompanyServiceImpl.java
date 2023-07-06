package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.CarrierCompany;
import com.cargotransportation.dao.Transport;
import com.cargotransportation.dto.CarrierCompanyDto;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.dto.requests.UpdateCarrierCompanyRequest;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.CarrierCompanyRepository;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.CarrierCompanyService;
import com.cargotransportation.services.TransportService;
import com.cargotransportation.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrierCompanyServiceImpl implements CarrierCompanyService {

    private final CarrierCompanyRepository carrierCompanyRepository;
    private final TransportService transportService;
    private final UserService userService;
    private final AuthService authService;
    private final UserRepository userRepository;

    public CarrierCompanyServiceImpl(UserRepository userRepository, UserService userService, AuthService authService, CarrierCompanyRepository carrierCompanyRepository, @Lazy TransportService transportService) {
        this.carrierCompanyRepository = carrierCompanyRepository;
        this.transportService = transportService;
        this.userService = userService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Override
    public List<CarrierCompanyDto> getAll() {
        return carrierCompanyRepository.findAll()
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CarrierCompanyDto findByUsername(String username){
        CarrierCompany carrierCompany = carrierCompanyRepository.findByUsername(username);
        if(carrierCompany == null){
            throw new NotFoundException("Carrier company with username '" + username + "' not found!");
        }
        return Converter.convert(carrierCompany);
    }


    @Override
    public CarrierCompanyDto findById(Long id) {
        CarrierCompany carrierCompany = carrierCompanyRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Carrier company with id '" + id + "' not found!"));
        List<Transport> transports = transportService.findAllByCarrierCompanyId(carrierCompany.getId())
                .stream()
                .map(dto ->
                        Transport.builder()
                                .id(dto.getId())
                                .model(dto.getModel())
                                .number(dto.getNumber())
                                .capacityInTons(dto.getCapacityInTons())
                                .type(dto.getType())
                                .carrier(Converter.convert(dto.getCarrier()))
                                .carrierCompany(carrierCompany)
                                .build()
                )
                .collect(Collectors.toList());
        carrierCompany.setCompanyTransports(transports);
        return Converter.convert(carrierCompany);
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
                .carrierCompany(carrierCompanyRepository.findById(dto.getCarrierCompanyId()).get())
                .build();
    }

    @Override
    public CarrierCompanyDto createTransports(List<CreateTransportRequest> requests) {
        CarrierCompany carrierCompany = Converter.convert(findById(
                findByUsername(
                        authService.getCurrentUserUsername()
                ).getId())
        );
        List<Transport> transports = new ArrayList<>();
        for(CreateTransportRequest request:requests){
            transports.add(convert(transportService.saveById(carrierCompany.getId(),request)));
        }
        carrierCompany.setCompanyTransports(transports);
        CarrierCompanyDto carrierCompanyDto = CarrierCompanyDto.builder()
                .id(carrierCompany.getId())
                .username(carrierCompany.getUsername())
                .role(carrierCompany.getRole().getName())
                .createdAt(carrierCompany.getCreatedAt())
                .isConfirmed(carrierCompany.isConfirmed())
                .fio(carrierCompany.getFio())
                .age(carrierCompany.getAge())
                .address(carrierCompany.getAddress())
                .phone(carrierCompany.getPhone())
                .percentToExpress(carrierCompany.getPercentToExpress())
                .percentToStandard(carrierCompany.getPercentToStandard())
                .pricePerKm(carrierCompany.getPricePerKm())
                .pricePerLb(carrierCompany.getPricePerLb())
                .companyTransports(transports.stream().map(Converter::convert).collect(Collectors.toList()))
                .companyName(carrierCompany.getCompanyName())
                .companyAddressDto(carrierCompany.getCompanyAddress())
                .build();
        return carrierCompanyDto;
    }

    @Override
    @Transactional
    public CarrierCompanyDto updatePrices(UpdateCarrierCompanyRequest request) {
        CarrierCompany carrierCompany = Converter.convert(findById(
                findByUsername(
                        authService.getCurrentUserUsername()
                ).getId()
        ));
        List<Transport> transports = transportService.findAllByCarrierCompanyId(carrierCompany.getId())
                .stream()
                .map(dto ->
                        Transport.builder()
                                .id(dto.getId())
                                .model(dto.getModel())
                                .number(dto.getNumber())
                                .capacityInTons(dto.getCapacityInTons())
                                .type(dto.getType())
                                .carrier(Converter.convert(dto.getCarrier()))
                                .carrierCompany(carrierCompany)
                                .build()
                )
                .collect(Collectors.toList());
        carrierCompany.setCompanyTransports(transports);
        String password = userRepository.findById(carrierCompany.getId()).get().getPassword();
        carrierCompany.setPricePerKm(request.getPricePerKm());
        carrierCompany.setPricePerLb(request.getPricePerLb());
        carrierCompany.setPercentToExpress(request.getPercentToExpress());
        carrierCompany.setPercentToStandard(request.getPercentToStandard());
        carrierCompany.setPassword(password);

        return Converter.convert(carrierCompanyRepository.save(carrierCompany));
    }
}
