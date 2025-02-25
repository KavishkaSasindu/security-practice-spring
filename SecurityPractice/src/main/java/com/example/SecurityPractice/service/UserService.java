package com.example.SecurityPractice.service;

import com.example.SecurityPractice.model.UserModel;
import com.example.SecurityPractice.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@NoArgsConstructor
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    get all users
    public List<UserModel> getAll() {
        List<UserModel> users = userRepo.findAll();

        if(users.isEmpty()) {
            return null;
        }

        return users;
    }
}
