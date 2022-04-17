package com.nals_test.todo.controller;

import com.nals_test.todo.controllers.TodoController;
import com.nals_test.todo.model.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;


@SpringBootTest
public class TodoController_findTodo {

    @Autowired
    private TodoController todoController;

    /**
     * test case page không tồn tại, không có dữ liệu trả về
     */
    @Test
    public void findTodo_page_not_found() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodo(null, null, null, null, PageRequest.of(99, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody().getContent().isEmpty());
    }

    /**
     * test case lấy về danh sách todo thành công
     */
    @Test
    public void findTodo_success() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodo(null, null, null, null, PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(20, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Đi tập gym",
                responseEntity.getBody().getContent().get(0).getWorkName());
    }

    /**
     * test case sắp xếp tăng dần theo starting date
     */
    @Test
    public void findTodo_success_sort() {
        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodo(null, null, null, null, PageRequest.of(0, 10, Sort.by("starting_date").ascending()));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(20, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Coding feature edit",
                responseEntity.getBody().getContent().get(0).getWorkName());
    }

    /**
     * test case tìm kiếm thành công theo work name
     */
    @Test
    public void findTodo_success_by_work_name() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodo("review", null, null, null, PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Review code",
                responseEntity.getBody().getContent().get(0).getWorkName());
        Assertions.assertEquals("2022-04-23",
                responseEntity.getBody().getContent().get(0).getEndingDate());
    }

    /**
     * test case tìm kiếm thành công theo status
     */
    @Test
    public void findTodo_success_status() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodo(null, 1, null, null, PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(6, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Hoàn thành đánh giá tuần",
                responseEntity.getBody().getContent().get(1).getWorkName());
        Assertions.assertEquals("2022-04-26",
                responseEntity.getBody().getContent().get(1).getEndingDate());
    }

    /**
     * test case tìm kiếm thành công theo khoảng thời gian starting date và ending date
     */
    @Test
    public void findTodo_success_startingDate_endingDate() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodo(null, null, "2022-04-20", "2022-04-30", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(12, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Ôn tập Java",
                responseEntity.getBody().getContent().get(4).getWorkName());
        Assertions.assertEquals("2022-04-27",
                responseEntity.getBody().getContent().get(4).getEndingDate());
    }

    /**
     * test case tìm kiếm thành công theo khoảng thời gian starting date và ending date
     */
    @Test
    public void findTodo_success_advanced() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodo("quiz", 0, "2022-04-20", "2022-04-25", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Quiz test angular",
                responseEntity.getBody().getContent().get(0).getWorkName());
        Assertions.assertEquals("2022-04-24",
                responseEntity.getBody().getContent().get(0).getEndingDate());
    }
}
