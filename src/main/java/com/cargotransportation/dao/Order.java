package com.cargotransportation.dao;

import com.cargotransportation.constants.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private User carrier;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private User shipper;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private User broker;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_address_id", referencedColumnName = "id")
    private Address sourceAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_address_id", referencedColumnName = "id")
    private Address destinationAddress;

    @Column(name = "volume")
    private Integer volume;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ProductType productType;

    @OneToMany(mappedBy = "order")
    private List<Document> documents;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "taken_date")
    private LocalDateTime takenDate;

    @Column(name = "delivered_date")
    private LocalDateTime deliveredDate;

    @Column(name = "estimated_delivery_date")
    private LocalDateTime estimatedDeliveryDate;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_location_id", referencedColumnName = "id")
    private Address currentLocation;

}
