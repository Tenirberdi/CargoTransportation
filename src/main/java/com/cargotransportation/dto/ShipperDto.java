package com.cargotransportation.dto;

import com.cargotransportation.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperDto {
    private UserDto user;
    private String status;
    private LocalDateTime createdAt;
//    private List<DocumentDto> documents;

}
