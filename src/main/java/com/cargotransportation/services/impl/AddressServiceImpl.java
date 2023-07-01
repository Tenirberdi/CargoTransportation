package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Address;
import com.cargotransportation.dto.AddressDto;
import com.cargotransportation.dto.requests.CreateAddressRequest;
import com.cargotransportation.repositories.AddressRepository;
import com.cargotransportation.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public AddressDto save(CreateAddressRequest request) {
        return Converter.convert(addressRepository.save(
                Address.builder()
                        .city(request.getCity())
                        .address(request.getAddress())
                        .state(request.getState())
                        .build()
        ));
    }
}
