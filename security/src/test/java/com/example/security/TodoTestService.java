package com.example.security;


import com.example.security.Model.MyUser;
import com.example.security.Model.Todo;
import com.example.security.Repository.AuthRepository;
import com.example.security.Repository.TodoRepository;
import com.example.security.Service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoTestService {

    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Mock
    AuthRepository authRepository;

    MyUser user;

    Todo todo1,todo2,todo3;

    List<Todo> todos;

    @BeforeEach
    void setUp() {
        user = new MyUser(null,"majd","123","Admin",null);
        todo1 = new Todo(null,"todo1",user);
        todo2 = new Todo(null,"todo2",user);
        todo3 = new Todo(null,"todo3",user);
        todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);
    }

    @Test
    public void getAllTodoTest(){
        when(todoRepository.findAll()).thenReturn(todos);
        List<Todo> todoList=todoService.getAllTodo();
        Assertions.assertEquals(3,todoList.size());
        verify(todoRepository,times(1)).findAll();
    }

    @Test
    public void getTodoByIdTest(){
        when(authRepository.findMyUserById(user.getId())).thenReturn(user);
        when(todoRepository.findAllByUser(user)).thenReturn(todos);

        List<Todo> todoList = todoService.getMyTodos(user.getId());
        Assertions.assertEquals(todoList,todos);
        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(todoRepository,times(1)).findAllByUser(user);
    }

    @Test
    public void addTodoTest(){

        when(authRepository.findMyUserById(user.getId())).thenReturn(user);

        todoService.addTodo(user.getId(),todo3);

        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(todoRepository,times(1)).save(todo3);
    }

    @Test
    public void updateTodoTest(){
        when(authRepository.findMyUserById(user.getId())).thenReturn(user);
        when(todoRepository.findTodoById(todo2.getId())).thenReturn(todo2);

        todoService.updateTodo(todo2.getId(),todo2, user.getId());

        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(todoRepository,times(1)).findTodoById(todo2.getId());
        verify(todoRepository,times(1)).save(todo2);
    }

    @Test
    public void deleteTodoTest(){
        when(authRepository.findMyUserById(user.getId())).thenReturn(user);
        when(todoRepository.findTodoById(todo2.getId())).thenReturn(todo2);

        todoService.deleteTodo(user.getId(), todo2.getId());

        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(todoRepository,times(1)).findTodoById(todo2.getId());
        verify(todoRepository,times(1)).delete(todo2);
    }

}
