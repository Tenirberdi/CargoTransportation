package com.cargotransportation.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String getCurrentUserUsername();
    String authorize(String username, String password);
    UserDetails getCurrentUser();
}
