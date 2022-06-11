package com.dev.cry.Controller;

import com.dev.cry.Entity.User;
import com.dev.cry.Filter.JwtAuthenticationFilter;
import com.dev.cry.Manager.AuthenticationManager;
import com.dev.cry.Service.JwtService;
import com.dev.cry.Service.UserService;
import com.dev.cry.Service.UserServiceImpl;
import com.dev.cry.model.JwtResponse;
import com.dev.cry.model.UserPrinciple;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginAction {

    final static Logger logger = Logger.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String username;
    private String password;
    private static String INPUT = "login/register";

    public ResponseEntity doDefault() {
        if (username != null && password != null) {
            User userDetails = userService.get(username);
            if(userDetails != null && checkPass(userDetails.getPassword(),password)){
                return handleUser(userDetails);
            }else{
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user = userService.registerUser(user);
                return handleUser(user);
            }
        }
        return null;
    }

    public ResponseEntity doExecute() {
        User userDetails = userService.get(username);
        if(userDetails != null && checkPass(userDetails.getPassword(),password)){
            return handleUser(userDetails);
        }
        return null;
    }

    public Boolean checkPass(String curr,String input){
        return passwordEncoder.matches(input,curr);
    }

    public ResponseEntity handleUser(User user){
        UserPrinciple userPrinciple = UserPrinciple.build(user);
        Authentication authentication = authenticationManager.authenticate(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(userPrinciple);
        return ResponseEntity.ok(new JwtResponse(user.getId(), jwt, "Bearer", userPrinciple.getUsername(), userPrinciple.getAuthorities()));
    }
}
