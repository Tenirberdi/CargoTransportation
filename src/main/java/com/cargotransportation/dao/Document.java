package com.cargotransportation.dao;

import com.cargotransportation.constants.DocumentType;
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
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "format")
    private String format;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private DocumentType type;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "carrier_id")
//    private Carrier carrier;
//
//    @ManyToOne
//    @JoinColumn(name = "shipper_id")
//    private Shipper shipper;
}
