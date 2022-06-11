package com.dev.cry.Service;

import com.dev.cry.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(User user);

    Iterable<User> findAll();

    User create(User user);

    User registerUser(User user);

    User get(String username);
}
