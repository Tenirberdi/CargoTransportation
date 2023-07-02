package com.cargotransportation.services;

public interface AuthService {
    String getCurrentUserUsername();
    String authorize(String username, String password);
}
