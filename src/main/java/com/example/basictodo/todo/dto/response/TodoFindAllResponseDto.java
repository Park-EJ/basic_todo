package com.example.basictodo.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoFindAllResponseDto {

    private final Long id;
    private final String content;

    public TodoFindAllResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}