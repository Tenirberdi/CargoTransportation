package com.cargocode.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "documents")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String path;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
