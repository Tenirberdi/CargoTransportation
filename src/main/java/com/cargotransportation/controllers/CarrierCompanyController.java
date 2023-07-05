package com.cargotransportation.controllers;

import com.cargotransportation.dao.CarrierCompany;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.dto.requests.UpdateCarrierCompanyRequest;
import com.cargotransportation.services.CarrierCompanyService;
import com.cargotransportation.services.TransportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carrier-companies")
public class CarrierCompanyController {

    private final TransportService transportService;
    private final CarrierCompanyService carrierCompanyService;


    @PostMapping("/transport/{carrierCompanyId}")
    public ResponseEntity<?> save(
            @PathVariable("carrierCompanyId") Long carrierCompanyId,
            @RequestBody List<CreateTransportRequest> request)
    {
        return new ResponseEntity<>(carrierCompanyService.createTransports(carrierCompanyId,request), HttpStatus.OK);
    }

    @PutMapping("/transport/{transportId}/{carrierId}")
    public ResponseEntity<?> setCarrierToTransport(
            @PathVariable("transportId") Long transportId,
            @PathVariable("carrierId") Long carrierId){

        return new ResponseEntity<>(transportService.setCarrierByTransportIdCarrierId(transportId,carrierId),HttpStatus.OK);
    }

    @PutMapping("/transport/{transportId}/set/{carrierId}")
    public ResponseEntity<?> setTransportToCarrier(@PathVariable("transportId") Long transportId,
                                                   @PathVariable("carrierId") Long carrierId){
        return new ResponseEntity<>(transportService.setCarrierByTransportIdCarrierId(transportId,carrierId),HttpStatus.OK);
    }

    @PutMapping("/{carrierCompanyId}/update-price-info")
    public ResponseEntity<?> updateCompanyPrice(
            @PathVariable("carrierCompanyId") Long carrierCompanyId,
            @RequestBody UpdateCarrierCompanyRequest request){
        return new ResponseEntity<>(carrierCompanyService.updatePricesByCompanyId(carrierCompanyId,request),HttpStatus.OK);
    }
}
