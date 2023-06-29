package com.cargocode.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentDto {
    Long id;

    @NonNull
    String path;
    @NonNull
    Long userId;
}
