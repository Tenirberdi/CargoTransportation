package com.cargotransportation.services;

import com.cargotransportation.dto.ShipperDto;
import com.cargotransportation.dto.requests.CreateShipperRequest;

public interface ShipperService {

    ShipperDto findById(Long id);

    ShipperDto create(CreateShipperRequest request);
}
