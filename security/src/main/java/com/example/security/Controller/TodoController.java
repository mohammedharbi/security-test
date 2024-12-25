package com.example.security.Controller;

import com.example.security.Api.ApiResponse;
import com.example.security.Model.MyUser;
import com.example.security.Model.Todo;
import com.example.security.Repository.TodoRepository;
import com.example.security.Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(todoService.getMyTodos(myUser.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Todo todo){
        todoService.addTodo(myUser.getId(), todo);
        return ResponseEntity.status(200).body(new ApiResponse("todo added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateMyTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer index, @RequestBody @Valid Todo todo){
        todoService.updateMyTodo(myUser.getId(), index, todo);
        return ResponseEntity.status(200).body(new ApiResponse("Todo updated"));
    }

    @DeleteMapping("delete/{index}")
    public ResponseEntity deleteMyTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer index){
        todoService.deleteMyTodo(myUser.getId(), index);
        return ResponseEntity.status(200).body(new ApiResponse("Todo deleted"));
    }
}