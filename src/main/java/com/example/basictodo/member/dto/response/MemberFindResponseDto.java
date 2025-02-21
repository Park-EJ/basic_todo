package com.example.basictodo.member.dto.response;

import lombok.Getter;

@Getter
public class MemberFindResponseDto {

    private final Long id;
    private final String email;

    public MemberFindResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

}