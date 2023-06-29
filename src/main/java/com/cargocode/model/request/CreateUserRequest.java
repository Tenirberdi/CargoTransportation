package com.cargocode.model.request;

import com.cargocode.model.utils.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @NonNull
    @NotEmpty
    String name;

    @NonNull
    @NotEmpty
    String surname;

    @NonNull
    @Email
    @NotEmpty
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

    @Enumerated(value = EnumType.STRING)
    @NonNull
    UserRole role;

    String physicalAddress;
}
