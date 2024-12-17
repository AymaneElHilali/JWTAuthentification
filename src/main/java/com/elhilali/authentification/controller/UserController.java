package com.elhilali.authentification.controller;
import com.elhilali.authentification.dataAcces.dto.UserDTO;
import com.elhilali.authentification.dataAcces.entity.User;
import com.elhilali.authentification.service.UserService;
import jakarta.validation.Valid;
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
    public UserDTO signup(@Valid @RequestBody UserDTO userdto){

        return userService.signup(userdto);
    }



    @PostMapping("/login")
    public UserDTO login(@Valid @RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }



    @GetMapping("/landingPage")
    public String landingPage(){
        return "pass";
    }


    @GetMapping("/landingPage2")
    public String landingPage2(){
        return "";
    }
}
