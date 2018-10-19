package com.depromeet.mannaja.service;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.repository.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class CalendarService {

    private CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public Calendar retrieveCalendar(Long memberId, String yearMonth) {
        Calendar calendar = calendarRepository.findByMemberIdAndYearMonth(memberId, yearMonth)
                .orElse(Calendar.createEmptyScheduleList(yearMonth));

        log.info("[CalendarService.retrieveCalendar] Success retrieve Calendar: {}", calendar);
        return calendar;
    }

    public Calendar checkExistCalendar(CalendarRequest request) {
        Calendar calendar = calendarRepository.findByMemberIdAndYearMonth(request.getMemberId(), request.getYearMonth())
                .orElseGet(() -> createCalendar(request));

        return calendar;
    }

    private Calendar createCalendar(CalendarRequest request) {
        Calendar calendar = Calendar.create(request);
        calendar = calendarRepository.save(calendar);

        log.info("[CalendarService.saveCalendar] Success save Calendar: {}", calendar);
        return calendar;
    }
}
