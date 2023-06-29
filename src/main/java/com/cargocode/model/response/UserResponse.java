package com.cargocode.model.response;

import com.cargocode.model.utils.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
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
    String contactNumber;

    String company;

    @NonNull
    @NotEmpty
    String mcDotNumber;

    String physicalAddress;

    @Enumerated(value = EnumType.STRING)
    @NotEmpty
    @NonNull
    UserRole role;

    @Builder.Default
    boolean isVerified = false;

    List<Long> documents;
}
