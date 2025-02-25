package com.example.SecurityPractice.service;

import com.example.SecurityPractice.model.UserModel;
import com.example.SecurityPractice.model.UserPrincipal;
import com.example.SecurityPractice.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Data
@Service
@NoArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepo.findByUsername(username);

        if(userModel == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userModel);
    }
}
