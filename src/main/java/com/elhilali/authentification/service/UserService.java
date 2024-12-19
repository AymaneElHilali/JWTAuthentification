package com.elhilali.authentification.service;

import com.elhilali.authentification.dataAcces.dto.*;
import com.elhilali.authentification.dataAcces.entity.User;
import com.elhilali.authentification.dataAcces.repository.UserRepo;
import com.elhilali.authentification.exception.ConflictException;
import com.elhilali.authentification.exception.NotFoundException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Builder
@Service
public class UserService {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepo userRepo;

    public SignupResponseDTO signup(SignupRequestDto signupRequestDto){

        if (findByEmail(signupRequestDto.getEmail())!=null){
            throw new ConflictException("Email already Used!");
        }
        User user = signupRequestDto.toEntity();
        User savedUser = userRepo.save(user);

        if (savedUser != null) {
            return new SignupResponseDTO(savedUser.getEmail(),"Signup successful");
        } else {
            // need a ApiException
            throw new  IllegalArgumentException("Signup failed");
        }


    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){

        User foundUser = this.findByEmail(loginRequestDTO.getEmail());
        if (foundUser != null && foundUser.getPassword().equals(loginRequestDTO.getPassword())) {
            String token = jwtService.generateToken(foundUser.getEmail());
            return new LoginResponseDTO(foundUser.getId(),foundUser.getEmail(),"Bearer " + token);
        } else {
            throw new NotFoundException("Wrong Email Or Pasword");
        }

    }
}
