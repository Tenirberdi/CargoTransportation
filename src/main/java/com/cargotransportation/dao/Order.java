package com.cargotransportation.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @Column(name = "name")
    private String name;

    @Column(name = "source_address")
    private String sourceAddress;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "taken_date")
    private LocalDateTime takenDate;

    @Column(name = "delivered_date")
    private LocalDateTime deliveredDate;

    @Column(name = "status")
    private String status;

    @Column(name = "current_location")
    private String currentLocation;

}
