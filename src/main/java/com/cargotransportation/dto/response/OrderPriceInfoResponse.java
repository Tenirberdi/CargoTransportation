package com.cargotransportation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPriceInfoResponse {
    private double km;
    private double volume;
    private int hours;
    private int minutes;

    private double tariffForDistance;
    private double tariffForVolume;
    private double percentByOrderType;

    private double totalDistancePrice;
    private double totalByOrderType;
    private double totalVolumePrice;
    private Double total;
}
