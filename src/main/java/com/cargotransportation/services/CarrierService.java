package com.cargotransportation.services;

import com.cargotransportation.dto.CarrierDto;

public interface CarrierService {
    CarrierDto findById(Long id);
}
