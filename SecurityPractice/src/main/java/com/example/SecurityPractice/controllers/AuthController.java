package com.example.SecurityPractice.controllers;

import com.example.SecurityPractice.dto.ErrorDto;
import com.example.SecurityPractice.model.UserModel;
import com.example.SecurityPractice.repo.UserRepo;
import com.example.SecurityPractice.service.AuthService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@NoArgsConstructor
public class AuthController {

    private AuthService authService;
    private UserRepo userRepo;

    @Autowired
    public AuthController(AuthService authService, UserRepo userRepo) {
        this.authService = authService;
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        try{
            if(user != null) {
                UserModel userModel = userRepo.findByUsername(user.getUsername());
                if(userModel == null) {
                    UserModel registeredUser = authService.registerUser(user);
                    if(registeredUser != null) {
                        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
                    }else{
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("user can not register"));
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("User already exists"));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("Provide register details"));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
        }
    }

//    login
    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody UserModel user) {
        try{
            if(user != null) {
                String username = authService.logInUSer(user);
                if(username != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ErrorDto("User logged in " +username));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("Provide login details"));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(e.getMessage()));
        }
    }

}
