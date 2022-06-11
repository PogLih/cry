package com.dev.cry.Service;

import com.dev.cry.CryApplication;
import com.dev.cry.Entity.User;
import com.dev.cry.Repository.UserRepository;
import com.dev.cry.model.UserPrinciple;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    final static Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
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
    public UserDetails loadUserByUsername(String username)  {
        User user = userRepository.findByUsername(username);
        if(user == null){
            logger.error("Null: "+User.class);
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(user);
    }

}
