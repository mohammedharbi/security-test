package com.example.security.Repository;

import com.example.security.Model.MyUser;
import com.example.security.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findAllByUser(MyUser myUser);

    Todo findTodoById(Integer id);
}
