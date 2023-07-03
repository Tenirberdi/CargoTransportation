package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.CarrierCompany;
import com.cargotransportation.dao.Role;
import com.cargotransportation.dto.CarrierCompanyDto;
import com.cargotransportation.dto.requests.CreateCarrierCompanyRequest;
import com.cargotransportation.repositories.CarrierCompanyRepository;
import com.cargotransportation.services.CarrierCompanyService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarrierCompanyServiceImpl implements CarrierCompanyService {

    private final CarrierCompanyRepository carrierCompanyRepository;


    @Override
    public CarrierCompanyDto save(CreateCarrierCompanyRequest request) {
        CarrierCompany carrierCompany = CarrierCompany.builder()
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(Role.builder().name(request.getRole()).build())
                .createdAt(LocalDateTime.now())
                .fio(request.getFio())
                .age(request.getAge())
                .address(request.getAddress())
                .phone(request.getPhone())

                .companyName(request.getCompanyName())
                .companyAddress(Converter.convert(request.getCompanyAddressDto()))
                .build();
        return Converter.convert(carrierCompanyRepository.save(carrierCompany));
    }

    @Override
    public List<CarrierCompanyDto> getAll() {
        return carrierCompanyRepository.findAll()
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }
}
