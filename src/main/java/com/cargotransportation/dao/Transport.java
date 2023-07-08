package com.cargotransportation.dao;

import com.cargotransportation.constants.TransportType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@EqualsAndHashCode(exclude = "carrier")
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

    @OneToOne
    @JoinColumn(name = "carrier_id")
    private User carrier;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "agent_id")
    private Agent agent;


}
