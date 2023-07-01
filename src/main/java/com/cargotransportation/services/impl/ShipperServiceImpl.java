package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.Shipper;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.ShipperDto;
import com.cargotransportation.dto.requests.CreateShipperRequest;
import com.cargotransportation.exception.carrier.CarrierNotFound;
import com.cargotransportation.repositories.ShipperRepository;
import com.cargotransportation.services.ShipperService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShipperServiceImpl implements ShipperService {

    private final ShipperRepository shipperRepository;
    private final UserService userService;


    @Override
    public ShipperDto findById(Long id) {
        Shipper shipper = shipperRepository.findById(id).orElseThrow(()->new CarrierNotFound(
                "Carrier with id '" + id + "' not found!",
                HttpStatus.NOT_FOUND
        ));
        return Converter.convert(shipper);
    }

    @Override
    public ShipperDto create(CreateShipperRequest request) {
        User user = Converter.convert(userService.findById(request.getUserId()));

        return Converter.convert(shipperRepository.save(
                Shipper.builder()
                        .user(user)
                        .status(request.getStatus())
                        .createdAt(request.getCreatedAt())
                        .build()
        ));
    }
}
