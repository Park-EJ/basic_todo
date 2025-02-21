package com.example.basictodo.member.dto.response;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
public class MemberSaveResponseDto {

    private final Long id;
    private final String email;
    private final String name;

    public MemberSaveResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

}