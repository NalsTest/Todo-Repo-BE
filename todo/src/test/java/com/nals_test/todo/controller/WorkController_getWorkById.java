package com.nals_test.todo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkController_getWorkById {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test case id không tồn tại trong database
     *
     * @throws Exception
     */
    @Test
    public void getWorkById_id_not_found() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/todo/{id}", "45"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case lấy thành công todo
     *
     * @throws Exception
     */
    @Test
    public void getWorkById_id_valid() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/todo/{id}", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
