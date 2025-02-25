package com.example.SecurityPractice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
public class ErrorDto {

    private String message;

    public ErrorDto(String message) {
        this.message = message;
    }
}
