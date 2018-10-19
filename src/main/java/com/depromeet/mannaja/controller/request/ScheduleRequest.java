package com.depromeet.mannaja.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ScheduleRequest {
    private String date;
    private Long calendarId;
}
