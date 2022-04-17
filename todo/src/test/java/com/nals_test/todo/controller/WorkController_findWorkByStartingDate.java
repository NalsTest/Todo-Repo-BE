package com.nals_test.todo.controller;

import com.nals_test.todo.controllers.WorkController;
import com.nals_test.todo.model.entity.Work;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class WorkController_findWorkByStartingDate {
    @Autowired
    private WorkController workController;

    /**
     * test case endingdate không có trong DB, không có dữ liệu trả về
     */
    @Test
    public void findTodoByStartingDate_ending_date_not_found() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWorkByStartingDate("2021-03-18", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody().getContent().isEmpty());
    }

    /**
     * test case tìm kiếm thành công
     */
    @Test
    public void findTodoByStartingDate_success() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWorkByStartingDate("2022-04-18", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Nấu bữa tối",
                responseEntity.getBody().getContent().get(0).getWorkName());
        Assertions.assertEquals("2022-04-18",
                responseEntity.getBody().getContent().get(0).getEndingDate());
    }
}
