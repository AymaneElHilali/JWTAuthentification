package com.elhilali.authentification.controller;

import com.elhilali.authentification.dataAcces.User;
import com.elhilali.authentification.service.UserService;
import com.elhilali.authentification.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;  // Inject JwtService to generate JWT tokens

    // Signup endpoint
    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        System.out.println(user.getEmail());
        return userService.signup(user);
    }

    // Login endpoint to generate JWT token
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Check if the admin exists and credentials are correct
        User foundUser = userService.findByEmail(user.getEmail());

        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            // If credentials are valid, generate JWT token
            String token = jwtService.generateToken(user.getEmail());
            return "Bearer " + token; // Return token in Bearer format
        } else {
            return "Invalid credentials"; // If credentials are invalid
        }
    }

    // Protected landing page (requires valid JWT token)
    @GetMapping("/landingPage")
    public String landingPage(){
        return "pass";
    }

    // Another protected landing page
    @GetMapping("/landingPage2")
    public String landingPage2(){
        return "pass";
    }
}
