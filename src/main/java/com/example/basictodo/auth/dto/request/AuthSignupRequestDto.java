package com.example.basictodo.auth.dto.request;

import lombok.Getter;

@Getter
public class AuthSignupRequestDto {

    private String email;
    private String password;

}