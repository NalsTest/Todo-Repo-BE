package com.nals_test.todo.controller;

import com.nals_test.todo.controllers.WorkController;
import com.nals_test.todo.model.entity.Work;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;


@SpringBootTest
public class WorkController_findWork {

    @Autowired
    private WorkController workController;

    /**
     * test case page không tồn tại, không có dữ liệu trả về
     */
    @Test
    public void findWork_page_not_found() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork(null, null, null, null, PageRequest.of(99, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody().getContent().isEmpty());
    }

    /**
     * test case lấy về danh sách todo thành công
     */
    @Test
    public void findWork_success() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork(null, null, null, null, PageRequest.of(0, 10));

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
    public void findWork_success_sort() {
        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork(null, null, null, null, PageRequest.of(0, 10, Sort.by("starting_date").ascending()));

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
    public void findWork_success_by_work_name() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork("review", null, null, null, PageRequest.of(0, 10));

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
    public void findWork_success_status() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork(null, 1, null, null, PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(6, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Hoàn thành đánh giá tuần",
                responseEntity.getBody().getContent().get(1).getWorkName());
        Assertions.assertEquals("2022-04-26",
                responseEntity.getBody().getContent().get(1).getEndingDate());
    }

    /**
     * test case tìm kiếm thành công theo startingDate
     */
    @Test
    public void findWork_success_startingDate() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork(null, null, "2022-04-20", null, PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Hoàn thành đánh giá tuần",
                responseEntity.getBody().getContent().get(0).getWorkName());
        Assertions.assertEquals("2022-04-26",
                responseEntity.getBody().getContent().get(0).getEndingDate());
    }

    /**
     * test case tìm kiếm thành công tổng hợp các item
     */
    @Test
    public void findWork_success_advanced() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork("xem", 0, "2022-04-19", "2022-04-19", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Xem 5 video tuts về aws",
                responseEntity.getBody().getContent().get(0).getWorkName());
        Assertions.assertEquals("2022-04-19",
                responseEntity.getBody().getContent().get(0).getEndingDate());
    }

    /**
     * test case tìm kiếm thành công theo ending date
     */
    @Test
    public void findWork_success_endingDate() {

        ResponseEntity<Page<Work>> responseEntity
                = this.workController.findWork(null, null, null, "2022-04-22", PageRequest.of(0, 10));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("Coding unit test",
                responseEntity.getBody().getContent().get(0).getWorkName());
        Assertions.assertEquals("2022-04-22",
                responseEntity.getBody().getContent().get(0).getEndingDate());
    }
}
