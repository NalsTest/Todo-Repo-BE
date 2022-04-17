package com.nals_test.todo.services;


import com.nals_test.todo.model.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TodoService {
    void add(Todo todo);

    void edit(Todo todo);

    Page<Todo> findAll(Pageable pageable);

    void delete(Integer id);

    Todo findById(Integer id);

    Page<Todo> searchTodo(String workName, Integer status, String startingDate, String endingDate, Pageable pageable);

    Page<Todo> searchTodoByStartingDate(String startingDate, Pageable pageable);

    Page<Todo> searchTodoByEndingDate(String endingDate, Pageable pageable);
}
