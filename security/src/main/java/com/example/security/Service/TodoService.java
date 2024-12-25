package com.example.security.Service;

import com.example.security.Api.ApiException;
import com.example.security.Model.MyUser;
import com.example.security.Model.Todo;
import com.example.security.Repository.AuthRepository;
import com.example.security.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final AuthRepository authRepository;

    private final TodoRepository todoRepository;

    public List<Todo> getMyTodos(Integer user_id){
        MyUser myUsers = authRepository.findMyUserById(user_id);
        if (myUsers == null){throw new ApiException("Wrong user id!");}

        return todoRepository.findAllByUser(myUsers);

    }

    public void addTodo(Integer user_id, Todo todo){
        MyUser myUsers = authRepository.findMyUserById(user_id);
        if (myUsers == null){throw new ApiException("Wrong user id!");}

        todo.setUser(myUsers);

        todoRepository.save(todo);
    }

    public void updateMyTodo(Integer user_id, Integer index, Todo todo){
        MyUser myUsers = authRepository.findMyUserById(user_id);
        if (myUsers == null){throw new ApiException("Wrong user id!");}

        Todo todo1 = todoRepository.findTodoById(index);
        if (todo1 == null){throw new ApiException("Todo not found");}

        if (!todo1.getUser().getId().equals(myUsers.getId())){throw new ApiException("Todo not belongs to the same user");}

        todo1.setTitle(todo.getTitle());
        todoRepository.save(todo1);
    }

    public void deleteMyTodo(Integer user_id, Integer index){
        MyUser myUsers = authRepository.findMyUserById(user_id);
        if (myUsers == null){throw new ApiException("Wrong user id!");}

        Todo todo = todoRepository.findTodoById(index);
        if (todo == null){throw new ApiException("Todo not found");}

        if (!todo.getUser().getId().equals(myUsers.getId())){throw new ApiException("Todo not belongs to the same user");}


        todoRepository.delete(todo);
    }
}
