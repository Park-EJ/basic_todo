package com.example.basictodo.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoFindByIdResponseDto {

    private final Long id;
    private final String content;
    private final Long memberId;

    public TodoFindByIdResponseDto(Long id, String content, Long memberId) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
    }
}