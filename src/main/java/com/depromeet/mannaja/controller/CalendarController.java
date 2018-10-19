package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.controller.resposne.CalendarResponse;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.service.CalendarService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "캘린더 가져오기", notes = "캘린더에 스케줄 정보를 담아서 리턴함. `yearMonth` 에는 `yyyy-mm` 형태. 스케줄 정보가 없을 시 빈 리스트를 반환함")
    @GetMapping("/calendar/{memberId}/{yearMonth}")
    public CalendarResponse getCalendar(
            @PathVariable("memberId") Long id,
            @PathVariable("yearMonth") String yearMonth) {

        return CalendarResponse.from(calendarService.retrieveCalendar(id, yearMonth));
    }
}
