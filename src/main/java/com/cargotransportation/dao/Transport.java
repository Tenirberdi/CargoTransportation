package com.cargotransportation.dao;

import com.cargotransportation.constants.TransportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transports")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "number")
    private String number;

    @Column(name = "capacity")
    private Integer capacityInTons;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private TransportType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User carrier;


}
