package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Calendar;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CalendarResponse {
    private String yearMonth;
    private List<ScheduleResponse> scheduleResponseList;

    public static CalendarResponse from(Calendar calendar) {
        CalendarResponse response = new CalendarResponse();
        response.yearMonth = calendar.getYearMonth();
        response.scheduleResponseList = calendar.getScheduleList()
                .stream()
                .map(schedule -> ScheduleResponse.from(response.yearMonth, schedule))
                .collect(Collectors.toList());

        return response;
    }
}
