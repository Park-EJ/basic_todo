package com.example.basictodo.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoFindResponseDto {

    private final Long id;
    private final String content;

    public TodoFindResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

}