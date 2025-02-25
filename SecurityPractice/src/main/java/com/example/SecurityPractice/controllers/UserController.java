package com.example.SecurityPractice.controllers;

import com.example.SecurityPractice.dto.ErrorDto;
import com.example.SecurityPractice.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Data
@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

  @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
        }
    }
}
