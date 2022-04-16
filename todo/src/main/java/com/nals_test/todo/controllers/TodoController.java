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
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @return todo
     * @author LinhDN
     * @description phương thức lấy ra 1 đối tượng todo bằng id
     * @since 16/04/2022 19:03
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable(value = "id") Integer id) {
        Todo todo = this.todoService.findById(id);
        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * @param id
     * @param todoDTO
     * @param bindingResult
     * @return trạng thái thêm mới thành công hay không
     * @description chỉnh sửa thông tin của todo theo id
     * @since 16/04/2022 19:03
     */
    @PatchMapping(value = "/{id}/edit")
    public ResponseEntity<Todo> editTodo(@PathVariable(value = "id") Integer id, @Valid @RequestBody TodoDTO todoDTO, BindingResult bindingResult) {
        new TodoDTO().validate(todoDTO, bindingResult);
        Todo todo = null;
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if ((todo = this.todoService.findById(id)) != null) {
            BeanUtils.copyProperties(todoDTO, todo);
            todo.setId(id);
            this.todoService.add(todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param id
     * @return trạng thái xoá thành công hay không
     * @description xoá todo theo id, bằng cách tạo trường boolean khi chuyển về true có nghĩa là xoá
     * @since 17/04/2022 03:45
     */
    @PatchMapping(value = "/{id}/delete")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("id") Integer id) {
        Todo todo = null;
        if ((todo = this.todoService.findById(id)) != null) {
            this.todoService.delete(id);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}