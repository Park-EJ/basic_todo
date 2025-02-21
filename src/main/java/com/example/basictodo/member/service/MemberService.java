package com.example.basictodo.member.service;

import com.example.basictodo.member.dto.request.MemberSaveRequestDto;
import com.example.basictodo.member.dto.request.MemberUpdateRequestDto;
import com.example.basictodo.member.dto.response.MemberFindResponseDto;
import com.example.basictodo.member.dto.response.MemberSaveResponseDto;
import com.example.basictodo.member.entity.Member;
import com.example.basictodo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버 생성
    @Transactional
    public MemberSaveResponseDto save(MemberSaveRequestDto dto) {
        Member member = new Member(dto.getEmail(), dto.getPassword());
        Member saved = memberRepository.save(member);
        return new MemberSaveResponseDto(saved.getId(), saved.getEmail());
    }

    // 멤버 전체 조회
    @Transactional(readOnly = true)
    public List<MemberFindResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> new MemberFindResponseDto(member.getId(), member.getEmail())).toList();
    }

    // 멤버 단건 조회
    @Transactional(readOnly = true)
    public MemberFindResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        return new MemberFindResponseDto(member.getId(), member.getEmail());
    }

    // 멤버 수정
    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        member.update(dto.getEmail());
    }

    // 멤버 삭제
    @Transactional
    public void delete(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.");
        }

        memberRepository.deleteById(memberId);
    }

}