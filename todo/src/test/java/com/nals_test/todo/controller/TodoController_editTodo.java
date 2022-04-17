package com.nals_test.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nals_test.todo.model.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoController_editTodo {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * test case trường hợp tất cả item đều hợp lệ
     *
     * @throws Exception
     */
    @Test
    public void editTodo_all_item_valid() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("Học Python");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2022-04-27");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * test case trường hợp id todo không tồn tại
     *
     * @throws Exception
     */
    @Test
    public void editTodo_id_not_found() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("Học Python");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2022-04-27");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "99")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp item null
     *
     * @throws Exception
     */
    @Test
    public void editTodo_item_null() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName(null);
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2022-04-27");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp work name dưới 3 ký tự
     *
     * @throws Exception
     */
    @Test
    public void editTodo_min_length_work_name() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("h");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2022-04-27");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp work name quá 255 ký tự
     *
     * @throws Exception
     */
    @Test
    public void editTodo_max_length_work_name() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("h");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2022-04-27");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp work name rỗng
     *
     * @throws Exception
     */
    @Test
    public void editTodo_empty_work_name() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2022-04-27");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp starting date sai định dạng
     *
     * @throws Exception
     */
    @Test
    public void editTodo_invalid_format_starting_date() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("Học C#");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2022-04");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp starting date trước thời gian hiện tại
     *
     * @throws Exception
     */
    @Test
    public void editTodo_invalid_before_now_starting_date() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("Học C#");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2021-04-14");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp ending date trước starting date
     *
     * @throws Exception
     */
    @Test
    public void editTodo_ending_date_before_starting_date() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("Học C#");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2021-04-30");
        todoDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * test case trường hợp status không thuộc khoảng giá trị 0 -> 2 (tươgn ứng với Planning, Doing, Complete)
     *
     * @throws Exception
     */
    @Test
    public void editTodo_invalid_status() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setWorkName("Học C#");
        todoDTO.setEndingDate("2022-04-27");
        todoDTO.setStartingDate("2021-04-30");
        todoDTO.setStatus(4);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(todoDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
