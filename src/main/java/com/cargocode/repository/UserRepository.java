package com.cargocode.repository;

import com.cargocode.model.dto.UserDto;
import com.cargocode.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM users WHERE email = :email",nativeQuery = true)
    User findUserByEmail(@Param("email") String email);
}
