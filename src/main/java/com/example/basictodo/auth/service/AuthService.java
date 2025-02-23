package com.example.basictodo.auth.service;

import com.example.basictodo.auth.dto.request.AuthLoginRequestDto;
import com.example.basictodo.auth.dto.response.AuthLoginResponsetDto;
import com.example.basictodo.auth.dto.request.AuthSignupRequestDto;
import com.example.basictodo.member.entity.Member;
import com.example.basictodo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 이메일은 이미 사용중입니다.");
        }

        Member saved = new Member(dto.getEmail(), dto.getPassword());
        memberRepository.save(saved);
    }

    // 로그인
    @Transactional(readOnly = true)
    public AuthLoginResponsetDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Email이 존재하지 않습니다."));

        if (!dto.getPassword().equals(member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        return new AuthLoginResponsetDto(member.getId());
    }

}