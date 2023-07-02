package com.cargotransportation.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cargotransportation.constants.DocumentType;
import com.cargotransportation.dto.DocumentDto;
import com.cargotransportation.dto.FileInfoDto;
import com.cargotransportation.dto.UserDto;
import com.cargotransportation.dto.response.ResponseMessage;
import com.cargotransportation.exception.FileLoadException;
import com.cargotransportation.services.DocumentService;
import com.cargotransportation.services.FilesStorageService;
import com.cargotransportation.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FilesController {
    private final FilesStorageService storageService;
    private final UserService userService;
    private final DocumentService documentService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    public FilesController(FilesStorageService storageService, UserService userService, DocumentService documentService) {
        this.storageService = storageService;
        this.userService = userService;
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam MultipartFile file,
                                                      @RequestParam DocumentType documentType,
                                                      @RequestParam(required = false) Long userId,
                                                      @RequestParam(required = false) Long orderId) {
        try {
            UserDto user = userService.findById(userId);
            if(user == null) {
                throw new FileLoadException("No such user with id: " + userId);
            }
            String fileName = String.format("%s.%s.%s", sdf.format(new Date()),
                    RandomStringUtils.randomAlphanumeric(8), file.getOriginalFilename());
            storageService.save(file, fileName);
            documentService.save(DocumentDto.builder()
                    .name(fileName)
                    .format(file.getContentType())
                    .type(documentType)
                    .orderId(orderId)
                    .userId(userId).build());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
        } catch (Exception e) {
            throw new FileLoadException("Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
        }
    }

    @GetMapping("/users/{id}/files")
    public List<FileInfoDto> getUserFiles(@PathVariable Long id) {
        return storageService.loadUserFiles(id);
    }

    @GetMapping("/orders/{id}/files")
    public List<FileInfoDto> getOrderFiles(@PathVariable Long id) {
        return storageService.loadOrderFiles(id);
    }

    @GetMapping("/document-types")
    public List<DocumentType> getDocumentTypes() {
        return documentService.getDocumentTypes();
    }

    @GetMapping(value = "/files/{filename:.+}",
    produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getFile(@PathVariable String filename) {
        return storageService.load(filename);
    }
}