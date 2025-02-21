package com.example.basictodo.auth.controller;

import com.example.basictodo.auth.dto.request.AuthLoginRequestDto;
import com.example.basictodo.auth.dto.request.AuthSignupRequestDto;
import com.example.basictodo.auth.dto.response.AuthLoginResponsetDto;
import com.example.basictodo.auth.service.AuthService;
import com.example.basictodo.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto dto) {
        authService.signup(dto);
    }

    // 로그인
    @PostMapping("/login")
    public void login(@RequestBody AuthLoginRequestDto dto, HttpServletRequest request) {
        AuthLoginResponsetDto result = authService.login(dto);

        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId());
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}