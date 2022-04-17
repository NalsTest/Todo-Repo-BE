package com.nals_test.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nals_test.todo.model.dto.WorkDTO;
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
public class WorkController_editWork {
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
    public void editWork_all_item_valid() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("Học Python");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2022-04-27");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_id_not_found() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("Học Python");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2022-04-27");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "99")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_item_null() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName(null);
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2022-04-27");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_min_length_work_name() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("h");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2022-04-27");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_max_length_work_name() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("h");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2022-04-27");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_empty_work_name() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2022-04-27");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_invalid_format_starting_date() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("Học C#");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2022-04");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_invalid_before_now_starting_date() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("Học C#");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2021-04-14");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_ending_date_before_starting_date() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("Học C#");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2021-04-30");
        workDTO.setStatus(1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
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
    public void editWork_invalid_status() throws Exception {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setWorkName("Học C#");
        workDTO.setEndingDate("2022-04-27");
        workDTO.setStartingDate("2021-04-30");
        workDTO.setStatus(4);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/todo/{id}/edit", "1")
                        .content(this.objectMapper.writeValueAsString(workDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
