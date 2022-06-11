package com.dev.cry.Controller;

import com.dev.cry.Entity.User;
import com.dev.cry.Service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class RegisterAction {
    private String username;
    private String password;
    private User user;
    private static String INPUT = "login/register";
    @Autowired
    private UserService userService;
    public User doDefault(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userService.create(user);
        return user;
    }
}
