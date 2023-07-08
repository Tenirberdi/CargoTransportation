package com.cargotransportation.services.impl;

import com.cargotransportation.dao.User;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.AuthService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @PersistenceContext
    private EntityManager em;
    private final UserRepository userRepository;

    private static final BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private String bcryptEncryptor(String plainText) {
        return passwordEcorder.encode(plainText);
    }

    private Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
        return passwordEcorder.matches(rawPassword, encodedPassword);
    }

    @Override
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername());
            em.detach(user);
            user.setPassword("");
            return user;
        } else {
            return null;
        }

    }

    @Override
    public String authorize(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            if(doPasswordsMatch(password, user.getPassword())) {
                return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            }
        }
        throw new BadCredentialsException("Bad credentials");
    }


}
