package com.example.basictodo.auth.service;

import com.example.basictodo.auth.dto.AuthSignupRequestDto;
import com.example.basictodo.member.entity.Member;
import com.example.basictodo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail(), dto.getPassword());
        memberRepository.save(member);
    }
}
