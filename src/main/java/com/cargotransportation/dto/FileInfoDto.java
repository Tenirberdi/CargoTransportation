package com.cargotransportation.dto;

import com.cargotransportation.constants.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoDto {
    private String name;
    private DocumentType documentType;
}
