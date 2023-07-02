package com.cargotransportation.services.impl;

import com.cargotransportation.dao.User;
import com.cargotransportation.repositories.UserRepository;
import com.cargotransportation.services.AuthService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthServiceImpl implements AuthService {
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
    public String getCurrentUserUsername() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
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
