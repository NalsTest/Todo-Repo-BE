package com.nals_test.todo.controller;

import com.nals_test.todo.controllers.TodoController;
import com.nals_test.todo.model.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class TodoController_findTodoByEndingDate {
    @Autowired
    private TodoController todoController;

    /**
     * test case endingdate không có trong DB, không có dữ liệu trả về
     */
    @Test
    public void findTodoByEndingDate_ending_date_not_found() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodoByEndingDate("2021-03-18", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody().getContent().isEmpty());
    }

    /**
     * test case tìm kiếm thành công
     */
    @Test
    public void findTodoByEndingDate_success() {

        ResponseEntity<Page<Todo>> responseEntity
                = this.todoController.findTodoByEndingDate("2022-04-19", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Xem 5 video tuts về aws",
                responseEntity.getBody().getContent().get(0).getWorkName());
        Assertions.assertEquals("2022-04-19",
                responseEntity.getBody().getContent().get(0).getEndingDate());
    }
}
