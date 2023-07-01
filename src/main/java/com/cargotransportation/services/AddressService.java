package com.cargotransportation.services;

import com.cargotransportation.dto.AddressDto;
import com.cargotransportation.dto.requests.CreateAddressRequest;

public interface AddressService {
    AddressDto save(CreateAddressRequest request);
}
