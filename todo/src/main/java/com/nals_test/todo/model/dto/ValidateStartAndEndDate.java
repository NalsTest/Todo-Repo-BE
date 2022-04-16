package com.nals_test.todo.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static Boolean checkDateCompareNow(String argStartDate) {
        boolean flag = true;
        Date startingDate = null;
        Date now = new Date(System.currentTimeMillis());
        // chỉnh thời gian hiện tại về 00:00:00 để chỉ so sánh ngày
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dateTime = calendar.getTime();
        try {
            startingDate = new SimpleDateFormat("yyyy-MM-dd").parse(argStartDate);

            if (startingDate.compareTo(dateTime) < 0) {
                flag = false;
            }
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }
}
