package com.cargocode.model.entity;

import com.cargocode.model.utils.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @NotEmpty
    String name;

    @NonNull
    @NotEmpty
    String surname;

    @Column(unique = true)
    @NonNull
    @NotEmpty
    @Email
    String email;

    @NonNull
    @NotEmpty
    String password;

    @NonNull
    @NotEmpty
    String contactNumber;

    String company;

    @NonNull
    @NotEmpty
    String mcDotNumber;

    String physicalAddress;

    @Enumerated(value = EnumType.STRING)
    @NonNull
    UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Document> documents;

}
