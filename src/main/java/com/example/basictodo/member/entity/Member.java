package com.example.basictodo.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void update(String name) {
        this.name = name;
    }

}