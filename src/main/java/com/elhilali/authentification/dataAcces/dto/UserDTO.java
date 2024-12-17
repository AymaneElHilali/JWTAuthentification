package com.elhilali.authentification.dataAcces.dto;

import com.elhilali.authentification.dataAcces.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    public UserDTO(String email) {
        this.email = email;
    }
    public UserDTO(String email,String jwt) {
        this.email = email;
        this.jwt = jwt;
    }

    @Email(message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,}$",
            message = "Password must contain at least 9 characters, one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;


    String jwt = null;

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }

}
