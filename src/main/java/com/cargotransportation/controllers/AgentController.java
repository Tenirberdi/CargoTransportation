package com.cargotransportation.controllers;

import com.cargotransportation.dto.TransportDto;
import com.cargotransportation.dto.requests.CreateCarrierRequest;
import com.cargotransportation.dto.requests.CreateTransportRequest;
import com.cargotransportation.dto.requests.UpdateAgentRequest;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.services.AuthService;
import com.cargotransportation.services.AgentService;
import com.cargotransportation.services.TransportService;
import com.cargotransportation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cargotransportation.constants.ResponseState.SUCCESS;

@RestController
@AllArgsConstructor
//@RequestMapping("/agent")
public class AgentController {

    private final TransportService transportService;
    private final AgentService agentService;
    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/transports")
    public Response save(@RequestBody List<CreateTransportRequest> request) {
        return new Response(SUCCESS, 0, agentService.createTransports(request));
    }

    @GetMapping("/agent/transports")
    public Response getAllTransports(){
        List<TransportDto> transports = transportService.findAllByCarrierCompanyId(authService.getCurrentUser().getId());
        return new Response(SUCCESS, 0, transports);
    }

    @PutMapping("/transport/{transportId}/carrier/{carrierId}")
    public Response setTransportToCarrier(@PathVariable("transportId") Long transportId,
                                                   @PathVariable("carrierId") Long carrierId){
        return new Response(SUCCESS, 0,
                transportService.setCarrierByTransportIdCarrierId(transportId,carrierId));
    }

    @PostMapping("/update-price-info")
    public Response updateCompanyPrice(
            @RequestBody UpdateAgentRequest request){
        return new Response(SUCCESS, 0, agentService.updatePrices(request));
    }

    @GetMapping("/agent/carriers")
    public Response getAllCarriers(){
        return new Response(SUCCESS, 0,
                userService.getAgentCarries());
    }

    @PostMapping("/agent/carriers")
    public Response createAgentCarrier(@RequestBody CreateCarrierRequest request) {
        return new Response(SUCCESS, 0,
                userService.save(request));
    }
//
//    @GetMapping("/transport/free")
//    public Response getAllFreeTransports(){
//        return new Response(SUCCESS, 0,
//                transportService.findByCarrierIsNullAndCarrierCompany());
//    }

}