package com.cargocode.repository;

import com.cargocode.model.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    @Query(value = "SELECT * FROM documents WHERE user_id = :id",nativeQuery = true)
    List<Document> findAllDocumentByUserId(@Param("id") Long id);



}
