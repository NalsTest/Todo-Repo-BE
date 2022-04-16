package com.nals_test.todo.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidateStartAndEndDate {

    public static Boolean checkDate(String argStartDate, String argEndDate) {
        boolean flag = true;
        Date startingDate = null;
        Date endingDate = null;

        try {
            startingDate = new SimpleDateFormat("yyyy-MM-dd").parse(argStartDate);
            endingDate = new SimpleDateFormat("yyyy-MM-dd").parse(argEndDate);
            if (startingDate.compareTo(endingDate) > 0) {
                flag = false;
            }
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }
}
