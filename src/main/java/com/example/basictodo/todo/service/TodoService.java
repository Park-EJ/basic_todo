package com.example.basictodo.todo.service;

import com.example.basictodo.member.entity.Member;
import com.example.basictodo.member.repository.MemberRepository;
import com.example.basictodo.todo.dto.request.TodoSaveRequestDto;
import com.example.basictodo.todo.dto.request.TodoUpdateRequestDto;
import com.example.basictodo.todo.dto.response.TodoFindResponseDto;
import com.example.basictodo.todo.dto.response.TodoSaveResponseDto;
import com.example.basictodo.todo.dto.response.TodoUpdateResponseDto;
import com.example.basictodo.todo.entity.Todo;
import com.example.basictodo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    public final MemberRepository memberRepository;

    // 일정 저장
    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        Todo todo = new Todo(dto.getContent(), member);

        Todo saved = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                saved.getId(),
                saved.getContent(),
                member.getId(),
                member.getEmail(),
                member.getName()
                );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<TodoFindResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();

        List<TodoFindResponseDto> dtos = new ArrayList<>();
        for (Todo todo : todos) {
            dtos.add(new TodoFindResponseDto(todo.getId(), todo.getContent()));
        }
        return dtos;
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public TodoFindResponseDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다."));

        return new TodoFindResponseDto(todo.getId(), todo.getContent());
    }

    // 일정 수정
    @Transactional
    public TodoUpdateResponseDto update(Long memberId, Long todoId, TodoUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다."));

        if (!todo.getMember().getId().equals(member.getId())) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 일정의 작성자가 아닙니다.");
        }

        todo.update(dto.getContent());

        return new TodoUpdateResponseDto(todo.getId(), todo.getContent());
    }

    // 일정 삭제
    @Transactional
    public void delete(Long memberId, Long todoId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다."));

        if (!todo.getMember().getId().equals(member.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 일정의 작성자가 아닙니다.");
        }

       todoRepository.deleteById(todoId);
    }

}