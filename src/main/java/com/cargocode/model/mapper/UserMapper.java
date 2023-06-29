package com.cargocode.model.mapper;

import com.cargocode.model.dto.UserDto;
import com.cargocode.model.entity.Document;
import com.cargocode.model.entity.User;
import com.cargocode.model.response.UserResponse;
import com.cargocode.service.DocumentService;
import com.cargocode.service.impl.DocumentServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Override
    default UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setSurname(entity.getSurname());
        userDto.setEmail(entity.getEmail());
        userDto.setPassword(entity.getPassword());
        userDto.setContactNumber(entity.getContactNumber());
        userDto.setCompany(entity.getCompany());
        userDto.setMcDotNumber(entity.getMcDotNumber());
        userDto.setPhysicalAddress(entity.getPhysicalAddress());
        userDto.setRole(entity.getRole());

        List<Long> documentIds = new ArrayList<>();
        if(entity.getDocuments() != null){
            for (Document document : entity.getDocuments()) {
                documentIds.add(document.getId());
            }
        }
        userDto.setDocuments(documentIds);

        return userDto;
    }

    @Override
    default User toEntity(UserDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setContactNumber(dto.getContactNumber());
        user.setCompany(dto.getCompany());
        user.setMcDotNumber(dto.getMcDotNumber());
        user.setPhysicalAddress(dto.getPhysicalAddress());
        user.setRole(dto.getRole());

        List<Document> documents = new ArrayList<>();
        DocumentService documentService = new DocumentServiceImpl();

        for (Long documentId : dto.getDocuments()) {
            Document document = DocumentMapper.INSTANCE.toEntity(documentService.getDocumentById(documentId));
            documents.add(document);
        }
        user.setDocuments(documents);

        return user;
    }

    default UserResponse dtoToResponse(UserDto dto){
        return UserResponse.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .contactNumber(dto.getContactNumber())
                .company(dto.getCompany())
                .mcDotNumber(dto.getMcDotNumber())
                .physicalAddress(dto.getPhysicalAddress())
                .role(dto.getRole())
                .isVerified(dto.isVerified())
                .documents(dto.getDocuments())
                .build();
    }

}
