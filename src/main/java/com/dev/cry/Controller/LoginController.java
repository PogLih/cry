package com.dev.cry.Controller;

import com.dev.cry.Entity.User;
import com.dev.cry.Manager.AuthenticationManager;
import com.dev.cry.Service.JwtService;
import com.dev.cry.Service.UserServiceImpl;
import com.dev.cry.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private LoginAction action;

    @GetMapping(value = "/register")
    public ResponseEntity<?> register(HttpServletRequest httpServletRequest) {
        String username = String.valueOf(httpServletRequest.getParameterValues("username")[0]);
        String password = String.valueOf(httpServletRequest.getParameterValues("password")[0]);
        action.setUsername(username);
        action.setPassword(password);
        return action.doDefault();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest httpServletRequest) {
        String username = String.valueOf(httpServletRequest.getParameterValues("username")[0]);
        String password = String.valueOf(httpServletRequest.getParameterValues("password")[0]);
        action.setUsername(username);
        action.setPassword(password);
        return action.doExecute();
    }
}
