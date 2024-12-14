package com.elhilali.authentification.service;

import com.elhilali.authentification.dataAcces.User;
import com.elhilali.authentification.dataAcces.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepo userRepo;

    public User signup(User user){
        return userRepo.save(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public String login(User user){

        User foundUser = this.findByEmail(user.getEmail());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            String token = jwtService.generateToken(user.getEmail());
            return "Bearer " + token;
        } else {
            return "Invalid credentials";
        }

    }
}
