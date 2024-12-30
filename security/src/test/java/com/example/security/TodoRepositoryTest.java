package com.example.security;

import com.example.security.Model.MyUser;
import com.example.security.Model.Todo;
import com.example.security.Repository.AuthRepository;
import com.example.security.Repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    AuthRepository authRepository;
    MyUser user;

    Todo todo1,todo2,todo3;

    List<Todo> todos;

    Set<Todo> todosSet;

    Todo todo;

    @BeforeEach
    void setUp() {
        user = new MyUser(null,"majd","123","admin",todosSet);
        authRepository.save(user);
        todo1 = new Todo(null, "todo1",user);
        todo2 = new Todo(null, "todo2",user);
        todo3 = new Todo(null, "todo3",user);
        todo = new Todo(null,"test",user);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);
        todoRepository.save(todo);
    }

    @Test
    public void findAllByUserTesting(){

        todos = todoRepository.findAllByUser(user);
        Assertions.assertThat(todos.get(0).getUser().getId().equals(user.getId()));
    }


    @Test
    public void findTodoById(){

      Todo todo4=  todoRepository.findTodoById(todo1.getId());
        Assertions.assertThat(todo1).isEqualTo(todo4);
    }

}
