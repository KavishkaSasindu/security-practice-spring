package com.example.SecurityPractice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
}
