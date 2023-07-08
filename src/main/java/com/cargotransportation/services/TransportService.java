package com.cargotransportation.services;

import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.requests.CreateTransportRequest;

import java.util.List;

public interface TransportService {
    TransportDto saveById(Long carrierCompanyId,CreateTransportRequest request);

    TransportDto setCarrierByTransportIdCarrierId(Long transportId, Long carrierId);

    List<TransportDto> findAllByCarrierCompanyId(Long id);

    List<TransportDto> findByCarrierIsNullAndCarrierCompany();

}
