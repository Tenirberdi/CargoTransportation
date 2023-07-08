package com.cargotransportation.services;

import com.cargotransportation.dao.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String authorize(String username, String password);
    User getCurrentUser();
}
