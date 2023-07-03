package com.cargotransportation.dao;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    private Address companyAddress;

    @Column(name = "price_per_lb")
    private int pricePerLb;

    @Column(name = "price_per_km")
    private int pricePerKm;

    @OneToMany
    private List<Transport> companyTransports;

}
