package com.depromeet.mannaja.utils;

import java.time.LocalDate;

public class DateUtils {
    public static LocalDate convertDate(String yearMonth, String day) {
        String[] arr = (yearMonth + "-" + day).split("-");
        return LocalDate.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
    }
}
