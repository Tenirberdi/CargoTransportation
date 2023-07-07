package com.cargotransportation.controllers;

import com.cargotransportation.dao.CarrierCompany;
import com.cargotransportation.dao.Role;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.dto.requests.UpdateCarrierCompanyRequest;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.CarrierCompanyService;
import com.cargotransportation.services.TransportService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
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
    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/transport")
    public ResponseEntity<?> save(
            @RequestBody List<CreateTransportRequest> request)
    {
        return new ResponseEntity<>(carrierCompanyService.createTransports(request), HttpStatus.OK);
    }

    @GetMapping("/transport")
    public ResponseEntity<?> getAllTransports(){
        return new ResponseEntity<>(transportService.findAllByCarrierCompanyId(
                carrierCompanyService.findById(
                        carrierCompanyService.findByUsername(
                                authService.getCurrentUserUsername()
                        ).getId()).getId()
        ),HttpStatus.OK);
    }

    @GetMapping("/transport/{transportId}/set/{carrierId}")
    public ResponseEntity<?> setTransportToCarrier(@PathVariable("transportId") Long transportId,
                                                   @PathVariable("carrierId") Long carrierId){
        return new ResponseEntity<>(transportService.setCarrierByTransportIdCarrierId(transportId,carrierId),HttpStatus.OK);
    }

    @PostMapping("/update-price-info")
    public ResponseEntity<?> updateCompanyPrice(
            @RequestBody UpdateCarrierCompanyRequest request){
        return new ResponseEntity<>(carrierCompanyService.updatePrices(request),HttpStatus.OK);
    }

    @GetMapping("/carrier")
    public ResponseEntity<?> getAllCarriers(){
        return new ResponseEntity<>(userService.findAllByRoleAndTransportIsNull("ROLE_CARRIER"),HttpStatus.OK);
    }


    @GetMapping("/transport/free")
    public ResponseEntity<?> getAllFreeTransports(){
        return new ResponseEntity<>(transportService.findByCarrierIsNullAndCarrierCompany(),HttpStatus.OK);
    }

}