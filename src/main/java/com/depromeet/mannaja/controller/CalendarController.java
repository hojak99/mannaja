package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.service.calendar.CalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class CalendarController {
    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar/{id}")
    public void getCalendar(
            @PathVariable("id") Long id) {
        calendarService.retrieveCalendar(id);
    }

    @PostMapping("/calendar")
    public void postCalendar(
            @Valid @RequestBody CalendarRequest request) {
        calendarService.createCalendar(request);
    }
}
