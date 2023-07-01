package com.cargotransportation.dto;

import com.cargotransportation.dao.Document;
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
public class CarrierDto {
    private UserDto user;
    private UserDto followingUser;
    private UserDto followedUser;
    private LocalDateTime createdAt;
//    private List<DocumentDto> documents;
}
