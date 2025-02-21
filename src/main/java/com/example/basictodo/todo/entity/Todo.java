package com.example.basictodo.todo.entity;

import com.example.basictodo.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    public Todo(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

}