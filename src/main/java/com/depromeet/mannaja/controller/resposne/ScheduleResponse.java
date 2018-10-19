package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Schedule;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ScheduleResponse {
    private Long id;
    private LocalDate date;
    private boolean isScheduled;

    public static ScheduleResponse from(String yearMonth, Schedule schedule) {
        ScheduleResponse response = new ScheduleResponse();
        response.id = schedule.getId();
        response.date = LocalDate.parse(combineDate(yearMonth, schedule));
        response.isScheduled = schedule.isScheduled();

        return response;
    }

    private static String combineDate(String yearMonth, Schedule schedule) {
        return yearMonth + "-" + schedule.getDate();
    }
}
