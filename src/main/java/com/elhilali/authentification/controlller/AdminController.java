package com.elhilali.authentification.controller;

import com.elhilali.authentification.dataAcces.Admin;
import com.elhilali.authentification.service.AdminService;
import com.elhilali.authentification.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    JwtService jwtService;  // Inject JwtService to generate JWT tokens

    // Signup endpoint
    @PostMapping("/signup")
    public Admin signup(@RequestBody Admin admin){
        System.out.println(admin.getEmail());
        return adminService.signup(admin);
    }

    // Login endpoint to generate JWT token
    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        // Check if the admin exists and credentials are correct
        Admin foundAdmin = adminService.findByEmail(admin.getEmail());

        if (foundAdmin != null && foundAdmin.getPassword().equals(admin.getPassword())) {
            // If credentials are valid, generate JWT token
            String token = jwtService.generateToken(admin.getEmail());
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
