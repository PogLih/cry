package com.dev.cry.Manager;

import com.dev.cry.Entity.User;
import com.dev.cry.Repository.UserRepository;
import com.dev.cry.model.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {
    @Autowired
    private UserRepository userRepository;
    public Authentication authenticate(User user){
        if(user != null) {
            UserPrinciple userPrinciple = UserPrinciple.build(user);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrinciple.getUsername(), userPrinciple.getPassword(), userPrinciple.getAuthorities());
            return authentication;
        }
        return null;
    }
}
