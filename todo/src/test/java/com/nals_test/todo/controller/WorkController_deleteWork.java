package com.nals_test.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkController_deleteWork {
    @Autowired
    private MockMvc mockMvc;


    /**
     * Test case id không tồn tại trong database
     *
     * @throws Exception
     */
    @Test
    public void deleteWork_id_not_found() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/delete", "45"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case id rỗng
     *
     * @throws Exception
     */
    @Test
    public void deleteWork_id_empty() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/delete", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case id tồn tại xoá thành công
     *
     * @throws Exception
     */
    @Test
    public void deleteWork_id_valid() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/delete", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
