package com.example.basictodo.todo.controller;

import com.example.basictodo.todo.dto.request.TodoSaveRequestDto;
import com.example.basictodo.todo.dto.request.TodoUpdateRequestDto;
import com.example.basictodo.todo.dto.response.TodoFindResponseDto;
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
    public ResponseEntity<TodoSaveResponseDto> save(@RequestBody TodoSaveRequestDto dto) {
        return ResponseEntity.ok(todoService.save(dto));
    }

    // 일정 전체 조회
    @GetMapping("/todos")
    public ResponseEntity<List<TodoFindResponseDto>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    // 일정 단건 조회
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoFindResponseDto> findById(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    // 일정 수정
    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponseDto> update(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto dto) {
        return ResponseEntity.ok(todoService.update(todoId, dto));
    }

    // 일정 삭제
    @DeleteMapping("/todos/{todoId}")
    public void delete(@PathVariable Long todoId) {
        todoService.delete(todoId);
    }

}
