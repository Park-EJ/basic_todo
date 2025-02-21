package com.example.basictodo.auth.dto.response;

import lombok.Getter;

@Getter
public class AuthLoginResponsetDto {

    private final Long memberId;

    public AuthLoginResponsetDto(Long memberId) {
        this.memberId = memberId;
    }
}
