package com.dev.cry.Controller;

import com.dev.cry.Entity.User;
import com.dev.cry.Service.JwtService;
import com.dev.cry.Service.UserService;
import com.dev.cry.Service.UserServiceImpl;
import com.dev.cry.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RegisterAction action;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/register")
    public User register(HttpServletRequest httpServletRequest) {
        String username = String.valueOf(httpServletRequest.getParameterValues("username")[0]);
        String password = String.valueOf(httpServletRequest.getParameterValues("password")[0]);
        action.setUsername(username);
        action.setPassword(password);
        return action.doDefault();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = null;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User curUser = (User) userService.loadUserByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(curUser.getId(), jwt, "Bearer", curUser.getUsername(), userDetails.getAuthorities()));
    }
}
