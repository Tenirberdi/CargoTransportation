package com.cargotransportation.dao;

import com.cargotransportation.constants.DocumentType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
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
    @JoinColumn(columnDefinition = "integer", name = "order_id",nullable = true)
    private Order order;

    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "user_id",nullable = true)
    private User user;
}
