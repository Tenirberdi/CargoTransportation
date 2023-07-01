package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Carrier;
import com.cargotransportation.dto.CarrierDto;
import com.cargotransportation.exception.carrier.CarrierNotFound;
import com.cargotransportation.repositories.CarrierRepository;
import com.cargotransportation.services.CarrierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepository carrierRepository;

    @Override
    public CarrierDto findById(Long id) {
        Carrier carrier = carrierRepository.findById(id).orElseThrow(()->new CarrierNotFound(
                "Carrier with id '" + id + "' not found!", HttpStatus.NOT_FOUND));
        return Converter.convert(carrier);
    }
}
