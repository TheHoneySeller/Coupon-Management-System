package com.faruk.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faruk.coupon.model.User;
import com.faruk.coupon.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postUser(User user) throws Exception {
        return  userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}
