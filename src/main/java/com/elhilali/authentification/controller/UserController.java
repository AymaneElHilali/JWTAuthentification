package com.elhilali.authentification.controller;
import com.elhilali.authentification.dataAcces.User;
import com.elhilali.authentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return userService.signup(user);
    }



    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return userService.login(user);

    }



    @GetMapping("/landingPage")
    public String landingPage(){
        return "pass";
    }


    @GetMapping("/landingPage2")
    public String landingPage2(){
        return "pass";
    }
}
