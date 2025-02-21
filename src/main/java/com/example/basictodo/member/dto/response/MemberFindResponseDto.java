package com.example.basictodo.member.dto.response;

import lombok.Getter;

@Getter
public class MemberFindResponseDto {

    private final Long id;
    private final String email;
    private final String name;

    public MemberFindResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

}