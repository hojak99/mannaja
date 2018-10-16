package com.depromeet.mannaja.service;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.repository.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class CalendarService {

    private CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public Calendar retrieveCalendar(Long memberId, LocalDate yearMonth) {
        Calendar calendar = calendarRepository.findByMemberIdAndYearMonth(memberId, yearMonth)
                .orElse(Calendar.createEmptyScheduleList());

        log.info("[CalendarService.retrieveCalendar] Success retrieve Calendar: {}", calendar);
        return calendar;
    }

    public Calendar createCalendar(CalendarRequest request) {
        Calendar calendar = Calendar.from(request);
        calendar = calendarRepository.save(calendar);

        log.info("[CalendarService.saveCalendar] Success save Calendar: {}", calendar);

        return calendar;
    }


}
