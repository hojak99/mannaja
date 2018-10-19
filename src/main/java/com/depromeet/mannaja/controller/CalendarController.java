package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.controller.resposne.CalendarResponse;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
public class CalendarController {
    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar/{memberId}/{yearMonth}")
    public CalendarResponse getCalendar(
            @PathVariable("memberId") Long id,
            @PathVariable("yearMonth") String yearMonth) {

        return CalendarResponse.from(calendarService.retrieveCalendar(id, yearMonth));
    }

    @PostMapping("/calendar")
    public void postCalendar(
            @Valid @RequestBody CalendarRequest request) {
        calendarService.createCalendar(request);
    }
}
