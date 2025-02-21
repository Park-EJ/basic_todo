package com.example.basictodo.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final String content;
    private final Long memberId;
    private final String memberEmail;
    private final String memberName;

    public TodoSaveResponseDto(Long id, String content, Long memberId, String memberEmail, String memberName) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
    }
}