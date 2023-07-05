package com.cargotransportation.services;

import com.cargotransportation.dto.CarrierCompanyDto;
import com.cargotransportation.dto.requests.CreateCarrierCompanyRequest;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.dto.requests.UpdateCarrierCompanyRequest;

import java.util.List;

public interface CarrierCompanyService {
    List<CarrierCompanyDto> getAll();
    CarrierCompanyDto findById(Long id);
    CarrierCompanyDto createTransports(List<CreateTransportRequest> requests);
    CarrierCompanyDto findByUsername(String username);

    CarrierCompanyDto updatePrices(UpdateCarrierCompanyRequest request);

}
