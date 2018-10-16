package com.depromeet.mannaja.service.calendar;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.repository.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalendarService {

    private CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public Calendar retrieveCalendar(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no calendar data : " + id));

        log.info("[CalendarService.retrieveCalendar] Success retrieve Calendar: {}", calendar);
        return calendar;
    }

    public void saveCalendar(CalendarRequest request) {
        Calendar calendar = Calendar.from(request);
        calendar = calendarRepository.save(calendar);

        log.info("[CalendarService.saveCalendar] Success save Calendar: {}", calendar);
    }
}
