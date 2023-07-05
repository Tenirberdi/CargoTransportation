package com.cargotransportation.services.impl;

import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.CarrierCompany;
import com.cargotransportation.dao.Transport;
import com.cargotransportation.dao.User;
import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.TransportRepository;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.CarrierCompanyService;
import com.cargotransportation.services.TransportService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final CarrierCompanyService carrierCompanyService;
    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    public TransportDto saveById(Long carrierCompanyId, CreateTransportRequest request) {
        CarrierCompany carrierCompany = Converter.convert(carrierCompanyService.findById(carrierCompanyId));

        Transport transport = Transport.builder()
                .capacityInTons(request.getCapacityInTons())
                .type(request.getType())
                .model(request.getModel())
                .number(request.getNumber())
                .carrierCompany(carrierCompany)
                .build();

        carrierCompany.getCompanyTransports().add(transport);

        return Converter.convert(transportRepository.save(transport));
    }

    @Override
    public TransportDto setCarrierByTransportIdCarrierId(Long transportId, Long carrierId) {
        User carrier = Converter.convert(userService.findById(carrierId));
        if(!carrier.getRole().getName().equals("ROLE_CARRIER"))
        {
            throw new NotFoundException("User with id '" + carrierId + "' is not carrier!");
        }

        Transport transport = transportRepository.findById(transportId)
                .orElseThrow(() -> new NotFoundException("Transport with id '" + transportId + "' not found!"));

        transport.setCarrier(carrier);
        carrier.setTransport(transport);
        transportRepository.save(transport);
        return TransportDto.builder()
                .id(carrier.getId())
                .model(carrier.getTransport().getModel())
                .number(carrier.getTransport().getNumber())
                .capacityInTons(transport.getCapacityInTons())
                .type(carrier.getTransport().getType())
                .carrier(Converter.convert(carrier))
                .carrierCompanyId(transport.getCarrierCompany().getId())
                .build();
    }

    @Override
    public List<TransportDto> findAllByCarrierCompanyId(Long id) {
        return transportRepository
                .findAllByCarrierCompany_Id(id)
                .stream()
                .map(Converter::convert)
                .toList();
    }


}
