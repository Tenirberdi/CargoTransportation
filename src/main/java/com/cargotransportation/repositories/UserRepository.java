package com.cargotransportation.repositories;

import com.cargotransportation.dao.Role;
import com.cargotransportation.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByRoleAndId(Role role, Long id);

    List<User> findByRoleAndTransportIsNull(Role role);
}
