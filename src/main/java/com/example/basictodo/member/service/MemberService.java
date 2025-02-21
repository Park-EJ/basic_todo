package com.example.basictodo.member.service;

import com.example.basictodo.member.dto.request.MemberSaveRequestDto;
import com.example.basictodo.member.dto.request.MemberUpdateRequestDto;
import com.example.basictodo.member.dto.response.MemberFindResponseDto;
import com.example.basictodo.member.dto.response.MemberSaveResponseDto;
import com.example.basictodo.member.entity.Member;
import com.example.basictodo.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버 생성
    @Transactional
    public MemberSaveResponseDto save(MemberSaveRequestDto dto) {
        Member member = new Member(dto.getEmail(), dto.getName());
        Member saved = memberRepository.save(member);
        return new MemberSaveResponseDto(saved.getId(), saved.getEmail(), saved.getName());
    }

    @Transactional(readOnly = true)
    // 멤버 전체 조회
    public List<MemberFindResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberFindResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(new MemberFindResponseDto(member.getId(), member.getEmail(), member.getName()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    // 멤버 단건 조회
    public MemberFindResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        return new MemberFindResponseDto(member.getId(), member.getEmail(), member.getName());
    }

    @Transactional
    // 멤버 수정
    public void update(Long memberId, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        member.update(dto.getEmail(), dto.getName());
    }

    @Transactional
    // 멤버 삭제
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        memberRepository.deleteById(memberId);
    }

}