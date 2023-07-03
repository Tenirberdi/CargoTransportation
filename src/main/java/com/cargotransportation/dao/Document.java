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

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private DocumentType type;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "order_id",nullable = true)
    private Order order;

    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "user_id",nullable = true)
    private User user;
}
