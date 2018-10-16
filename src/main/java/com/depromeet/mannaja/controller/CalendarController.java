package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.service.CalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class CalendarController {
    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar/{id}")
    public void getCalendar(
            @PathVariable("id") Long id,
            @RequestParam(name = "yearMonth") LocalDate yearMonth) {
        calendarService.retrieveCalendar(id, yearMonth);
    }

    @PostMapping("/calendar")
    public void postCalendar(
            @Valid @RequestBody CalendarRequest request) {
        calendarService.createCalendar(request);
    }
}
