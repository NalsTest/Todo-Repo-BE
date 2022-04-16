package com.nals_test.todo.model.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class TodoDTO implements Validator {
    private Integer id;

    @NotBlank(message = "This field cannot be left blank.")
    @Size(message = "Invalid field length.", min = 3, max = 255)
    private String workName;

    @NotBlank(message = "This field cannot be left blank.")
    @Size(message = "Invalid field length.", min = 10, max = 22)
    private String startingDate;

    @NotBlank(message = "This field cannot be left blank.")
    @Size(message = "Invalid field length.", min = 10, max = 22)
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")
    private String endingDate;

    public TodoDTO() {
    }

    public TodoDTO(Integer id, String workName, String startingDate, String endingDate) {
        this.id = id;
        this.workName = workName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TodoDTO todoDTO = (TodoDTO) target;
        String startDate = todoDTO.startingDate;
        String endDate = todoDTO.endingDate;
        if (!ValidateStartAndEndDate.checkDate(startDate, endDate)) {
            errors.rejectValue("endingDate", "endingDate.invalid");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

}
