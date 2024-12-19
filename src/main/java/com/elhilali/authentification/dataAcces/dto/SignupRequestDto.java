package com.elhilali.authentification.dataAcces.dto;

import com.elhilali.authentification.dataAcces.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class SignupRequestDto extends LoginRequestDTO{


    public User toEntity(){
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .build();
    }
}
