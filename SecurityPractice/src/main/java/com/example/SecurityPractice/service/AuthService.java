package com.example.SecurityPractice.service;

import com.example.SecurityPractice.dto.ErrorDto;
import com.example.SecurityPractice.model.UserModel;
import com.example.SecurityPractice.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@NoArgsConstructor
public class AuthService {

    private UserRepo userRepo;
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepo userRepo,AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
    }

//    register user in db
    public UserModel registerUser(UserModel userModel) {
        UserModel user = userRepo.findByUsername(userModel.getUsername());

        if(user == null){
            userModel.setPassword(bcrypt.encode(userModel.getPassword()));
            return userRepo.save(userModel);
        }
        return null;
    }

//    login user
    public String logInUSer(UserModel userModel) {
        UserModel user = userRepo.findByUsername(userModel.getUsername());
        if(user == null) {
            return null;
        }
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword()));

        if(authentication.isAuthenticated()) {
            return user.getUsername();
        }
        return "user is not authenticate";

    }
}
