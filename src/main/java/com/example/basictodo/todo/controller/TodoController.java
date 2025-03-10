package com.example.basictodo.todo.controller;

import com.example.basictodo.common.consts.Const;
import com.example.basictodo.todo.dto.request.TodoSaveRequestDto;
import com.example.basictodo.todo.dto.request.TodoUpdateRequestDto;
import com.example.basictodo.todo.dto.response.TodoFindAllResponseDto;
import com.example.basictodo.todo.dto.response.TodoFindByIdResponseDto;
import com.example.basictodo.todo.dto.response.TodoSaveResponseDto;
import com.example.basictodo.todo.dto.response.TodoUpdateResponseDto;
import com.example.basictodo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 일정 생성
    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody TodoSaveRequestDto dto
    ) {
        return ResponseEntity.ok(todoService.save(memberId, dto));
    }

    // 일정 전체 조회
    @GetMapping("/todos")
    public ResponseEntity<List<TodoFindAllResponseDto>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    // 일정 단건 조회
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoFindByIdResponseDto> findById(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    // 일정 수정
    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(todoService.update(memberId, todoId, dto));
    }
    // 일정 삭제
    @DeleteMapping("/todos/{todoId}")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId
    ) {
        todoService.delete(memberId, todoId);
    }

}