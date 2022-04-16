package com.nals_test.todo.services;

import com.nals_test.todo.model.entity.Todo;
import com.nals_test.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void add(Todo todo) {
        if (todo != null) {
            this.todoRepository.save(todo);
        }
    }

    @Override
    public void edit(Todo todo) {
        this.todoRepository.save(todo);
    }

    @Override
    public List<Todo> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Todo findById(Integer id) {
        return this.todoRepository.findById(id).orElse(null);
    }
}
