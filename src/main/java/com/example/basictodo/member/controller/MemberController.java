package com.example.basictodo.member.controller;

import com.example.basictodo.common.consts.Const;
import com.example.basictodo.member.dto.request.MemberUpdateRequestDto;
import com.example.basictodo.member.dto.response.MemberFindResponseDto;
import com.example.basictodo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 멤버 전체 조회
    @GetMapping("/members")
    public ResponseEntity<List<MemberFindResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    // 멤버 단건 조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberFindResponseDto> findById(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    // 멤버 수정
    @PutMapping("/members")
    public void update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto dto) {
        memberService.update(memberId, dto);
    }

    // 멤버 삭제
    @DeleteMapping("/members")
    public void delete(@SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId) {
        memberService.delete(memberId);
    }

}