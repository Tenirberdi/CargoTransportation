package com.cargotransportation.dto.requests;

import com.cargotransportation.constants.DocumentType;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.UserDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocumentRequest {

    private String format;

    @Enumerated(value = EnumType.STRING)
    private DocumentType type;

    private String location;

}
