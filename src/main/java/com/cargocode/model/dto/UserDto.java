package com.cargocode.model.dto;

import com.cargocode.model.entity.Document;
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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;

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

    String physicalAddress;

    @Enumerated(value = EnumType.STRING)
    @NonNull
    UserRole role;

    @Builder.Default
    boolean isVerified = false;

    List<Long> documents;

}
