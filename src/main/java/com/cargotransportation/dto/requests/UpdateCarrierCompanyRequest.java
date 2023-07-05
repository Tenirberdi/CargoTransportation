package com.cargotransportation.dto.requests;


import com.cargotransportation.dto.TransportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarrierCompanyRequest {

    private int pricePerLb;
    private int pricePerKm;
    private int percentToExpress;
    private int percentToStandard;

}
