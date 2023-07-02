package com.cargotransportation.repositories;

import com.cargotransportation.dao.Document;
import com.cargotransportation.dto.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    @Query(value = "SELECT name, type from documents where user_id = ?1", nativeQuery = true)
    List<FileInfo> getUsersFiles(Long userId);
    @Query(value = "SELECT name, type from documents where order_id = ?1", nativeQuery = true)
    List<FileInfo> getOrderFiles(Long orderId);
}
