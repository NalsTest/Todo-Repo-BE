package com.nals_test.todo.services;

import com.nals_test.todo.model.entity.Todo;
import com.nals_test.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
    public Page<Todo> findAll(Pageable pageable) {
        return this.todoRepository.getAllTodo(pageable);
    }

    @Override
    public void delete(Integer id) {
        this.todoRepository.deleteTodo(id);
    }

    @Override
    public Todo findById(Integer id) {
        return this.todoRepository.getTodoById(id);
    }

    @Override
    public Page<Todo> searchTodo(String workName, Integer status, String startingDate, String endingDate, Pageable pageable) {
        return this.todoRepository.searchTodo(workName, status, startingDate, endingDate, pageable);
    }

    @Override
    public Page<Todo> searchTodoByStartingDate(String startingDate, Pageable pageable) {
        return this.todoRepository.searchTodoByStartingDate(startingDate, pageable);
    }

    @Override
    public Page<Todo> searchTodoByEndingDate(String endingDate, Pageable pageable) {
        return this.todoRepository.searchTodoByEndingDate(endingDate, pageable);
    }
}
