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

    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }
    public List<Todo> getMyTodos(Integer user_id){
        MyUser myUsers = authRepository.findMyUserById(user_id);
        if (myUsers == null){throw new ApiException("Wrong user id!");}

        return todoRepository.findAllByUser(myUsers);

    }

    public Todo getTodoById(Integer id , Integer auth){
        Todo todo=todoRepository.findTodoById(id);
        if (todo==null){
            throw new ApiException("Todo not Found");
        }
        if (todo.getUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to get this Todo!");
        }
        return todo;
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

    public void updateTodo(Integer id , Todo newTodo , Integer auth){
        Todo oldTodo=todoRepository.findTodoById(id);
        MyUser myUser=authRepository.findMyUserById(auth);

        if (oldTodo==null){
            throw new ApiException("Todo not found");
        }else if(oldTodo.getUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to update this Todo!");
        }

        newTodo.setId(id);
        newTodo.setUser(myUser);

        todoRepository.save(newTodo);
    }

    public void deleteMyTodo(Integer user_id, Integer index){
        MyUser myUsers = authRepository.findMyUserById(user_id);
        if (myUsers == null){throw new ApiException("Wrong user id!");}

        Todo todo = todoRepository.findTodoById(index);
        if (todo == null){throw new ApiException("Todo not found");}

        if (!todo.getUser().getId().equals(myUsers.getId())){throw new ApiException("Todo not belongs to the same user");}


        todoRepository.delete(todo);
    }

    public void deleteTodo( Integer userId, Integer todoId) {
        Todo todo=todoRepository.findTodoById(todoId);

        if(todo.getUser().getId()!=userId){
            throw  new ApiException("you dont have authority");
        }

        todoRepository.delete(todo);
    }
}
