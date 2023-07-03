package com.cargotransportation.services;

import com.cargotransportation.dto.CarrierCompanyDto;
import com.cargotransportation.dto.requests.CreateCarrierCompanyRequest;

import java.util.List;

public interface CarrierCompanyService {
    CarrierCompanyDto save(CreateCarrierCompanyRequest request);
    List<CarrierCompanyDto> getAll();
}
