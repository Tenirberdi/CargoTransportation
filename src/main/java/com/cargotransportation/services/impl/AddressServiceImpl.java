package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Address;
import com.cargotransportation.dto.AddressDto;
import com.cargotransportation.dto.requests.CreateAddressRequest;
import com.cargotransportation.repositories.AddressRepository;
import com.cargotransportation.services.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
//    @PreAuthorize("hasAuthority('address.edit')")
    public AddressDto save(CreateAddressRequest request) {
        Address address = Address.builder()
                .city(request.getCity())
                .address(request.getAddress())
                .state(request.getState())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .build();
        log.info("Address created: " + address);
        return Converter.convert(addressRepository.save(
                address
        ));
    }

}
