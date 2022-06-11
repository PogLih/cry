package com.dev.cry.Service;

import com.dev.cry.CryApplication;
import com.dev.cry.Entity.Role;
import com.dev.cry.Entity.User;
import com.dev.cry.Repository.UserRepository;
import com.dev.cry.model.UserPrinciple;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    final static Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    public UserServiceImpl() {
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registerUser(User user) {
        User newUser = user;
        Role role = roleService.findRoleByName("lv1");
        Set roles = new HashSet();
        roles.add(role);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("Null: " + User.class);
            return null;
        }
        return UserPrinciple.build(user);
    }

    @Override
    public User get(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("Null: " + User.class);
            return null;
        }
        return user;
    }

}
