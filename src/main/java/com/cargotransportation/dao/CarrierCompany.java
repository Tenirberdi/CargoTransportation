package com.cargotransportation.dao;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrier_companies")
public class CarrierCompany extends User{

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "price_per_lb")
    private int pricePerLb;

    @Column(name = "price_per_km")
    private int pricePerKm;

    @Column(name = "percent_to_express")
    private int percentToExpress;

    @Column(name = "percent_to_standard")
    private int percentToStandard;

    @OneToMany(mappedBy = "carrierCompany",cascade = CascadeType.MERGE)
    private List<Transport> companyTransports = new ArrayList<>();

}
