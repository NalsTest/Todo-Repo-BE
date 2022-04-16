package com.nals_test.todo.controllers;

import com.nals_test.todo.model.dto.TodoDTO;
import com.nals_test.todo.model.entity.Todo;
import com.nals_test.todo.services.TodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin()
@RequestMapping(value = "api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping()
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoDTO todoDTO, BindingResult bindingResult) {
        new TodoDTO().validate(todoDTO, bindingResult);
        Todo todo = new Todo();
        if (!bindingResult.hasErrors()) {
            BeanUtils.copyProperties(todoDTO, todo);
            todo.setStatus(0);
            todo.setFlagDelete(false);
            this.todoService.add(todo);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{id}/edit")
    public ResponseEntity<Todo> createTodo(@PathVariable(value = "id")Integer id, @Valid @RequestBody TodoDTO todoDTO, BindingResult bindingResult) {
        new TodoDTO().validate(todoDTO, bindingResult);
        Todo todo = new Todo();
        if (!bindingResult.hasErrors()) {
            BeanUtils.copyProperties(todoDTO, todo);
            todo.setId(id);
            this.todoService.add(todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
