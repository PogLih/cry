package com.dev.cry.Manager;

import com.dev.cry.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationManager {
    @Autowired
    private UserRepository userRepository;
    public Authentication authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){

        return null;
    }
}
