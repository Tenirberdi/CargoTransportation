package com.cargotransportation.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAgentRequest {

    private int pricePerLb;
    private int pricePerKm;
    private int percentToExpress;
    private int percentToStandard;

}
