package com.elhilali.authentification.service;

import com.elhilali.authentification.dataAcces.dto.UserDTO;
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

    public UserDTO signup(UserDTO userdto){

        if (findByEmail(userdto.getEmail())!=null){
            throw new ConflictException("Email already Used!");
        }
        User user = userdto.toEntity();
        User savedUser = userRepo.save(user);

        if (savedUser != null) {
            return new UserDTO(savedUser.getEmail());
        } else {
            // need a ApiException
            throw new  IllegalArgumentException("Signup failed");
        }


    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserDTO login(UserDTO userDTO){

        User foundUser = this.findByEmail(userDTO.getEmail());
        if (foundUser != null && foundUser.getPassword().equals(userDTO.getPassword())) {
            String token = jwtService.generateToken(foundUser.getEmail());
            return new UserDTO(foundUser.getEmail(),"Bearer " + token);
        } else {
            throw new NotFoundException("Wrong Email Or Pasword");
        }

    }
}
