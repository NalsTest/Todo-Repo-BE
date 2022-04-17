package com.nals_test.todo.controllers;

import com.nals_test.todo.model.dto.TodoDTO;
import com.nals_test.todo.model.entity.Todo;
import com.nals_test.todo.services.TodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    /**
     * @param pageable
     * @return page todo
     * @description Tìm kiếm todo tổng hợp nhiều điều kiện:  gần đúng theo workname, theo status, theo khoảng thời gian bắt đầu và kết thúc
     *  @since 17/04/2022 14:45
     */
    @GetMapping()
    public ResponseEntity<Page<Todo>> findTodo(@RequestParam(name = "workName", required = false) String workName,
                                                  @RequestParam(name = "status", required = false) Integer status,
                                                  @RequestParam(name = "startingDate", required = false) String startingDate,
                                                  @RequestParam(name = "endingDate", required = false) String endingDate,
                                                  Pageable pageable) {
        Page<Todo> todos = this.todoService.searchTodo(workName, status, startingDate, endingDate, pageable);
            return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    /**
     * @param pageable
     * @return page todo
     * @description Tìm kiếm todo chính xác theo ngày bắt đầu
     * @since 17/04/2022 15:45
     */
    @GetMapping("/startingDate")
    public ResponseEntity<Page<Todo>> findTodoByStartingDate(@RequestParam(name = "date", required = false) String startingDate,
                                                  Pageable pageable) {
        Page<Todo> todos = this.todoService.searchTodoByStartingDate(startingDate, pageable);
            return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * @param pageable
     * @return page todo
     * @description Tìm kiếm todo chính xác theo ngày kết thúc
     * @since 17/04/2022 15:45
     */
    @GetMapping("/endingDate")
    public ResponseEntity<Page<Todo>> findTodoByEndingDate(@RequestParam(name = "date", required = false) String endingDate,
                                                             Pageable pageable) {
        Page<Todo> todos = this.todoService.searchTodoByEndingDate(endingDate, pageable);
            return new ResponseEntity<>(todos, HttpStatus.OK);
    }
}