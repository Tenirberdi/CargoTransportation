package com.cargotransportation.services;

import java.util.List;

import com.cargotransportation.dto.FileInfoDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    void init();

    void save(MultipartFile file, String fileName);

    Resource load(String filename);

    void deleteAll();

    List<FileInfoDto> loadUserFiles(Long userId);
    List<FileInfoDto> loadOrderFiles(Long userId);
}