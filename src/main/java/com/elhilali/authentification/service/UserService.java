package com.elhilali.authentification.service;

import com.elhilali.authentification.dataAcces.User;
import com.elhilali.authentification.dataAcces.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    public User signup(User user){
        return userRepo.save(user);

    }
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
