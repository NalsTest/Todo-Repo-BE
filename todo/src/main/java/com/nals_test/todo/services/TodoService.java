package com.nals_test.todo.services;


import com.nals_test.todo.model.entity.Todo;

import java.util.List;

public interface TodoService {
    void add(Todo todo);

    void edit(Todo todo);

    List<Todo> findAll();

    void delete(Integer id);
}
