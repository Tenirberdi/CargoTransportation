package com.cargotransportation.services.impl;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import com.cargotransportation.dto.FileInfoDto;
import com.cargotransportation.exception.FileLoadException;
import com.cargotransportation.services.DocumentService;
import com.cargotransportation.services.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("uploads");
    private final DocumentService documentService;

    public FilesStorageServiceImpl(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new FileLoadException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, String fileName) {
        try {
            if(!Files.exists(this.root)) {
                init();
            }
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new FileLoadException("A file of that name already exists.");
            }
            throw new FileLoadException(e.getMessage());
        }
    }

    @Override
//    @PreAuthorize("hasAuthority('file.read')")
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileLoadException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new FileLoadException("Error: " + e.getMessage());
        }
    }

    @Override
//    @PreAuthorize("hasAuthority('file.edit')")
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
//    @PreAuthorize("hasAuthority('file.read')")
    public List<FileInfoDto> loadUserFiles(Long userId) {
        return documentService.getUserFiles(userId);
    }

    @Override
//    @PreAuthorize("hasAuthority('file.read')")
    public List<FileInfoDto> loadOrderFiles(Long orderId) {
        return documentService.getOrderFiles(orderId);
    }
}