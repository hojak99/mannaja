package com.depromeet.mannaja.service;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.controller.request.ScheduleRequest;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Schedule;
import com.depromeet.mannaja.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Slf4j
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    private CalendarService calendarService;

    public ScheduleService(ScheduleRepository scheduleRepository, CalendarService calendarService) {
        this.scheduleRepository = scheduleRepository;
        this.calendarService = calendarService;
    }

    public Schedule retrieveSchedule(Long memberId, ScheduleRequest request) {
        Calendar calendar = calendarService.retrieveCalendar(memberId, request.getYearMonth());

        if(calendar.isIdNull()) {
            createCalendar(calendar, memberId, request);
        }

        request.setCalendarId(calendar.getId());

        Schedule schedule = findByCalenedarIdAndDate(request);

        log.info("[ScheduleService.changeIsScheduled] Success retrieve schedule : {}", schedule);
        return schedule;
    }

    private Schedule findByCalenedarIdAndDate(ScheduleRequest request) {
        return scheduleRepository.findByCalendarIdAndDate(request.getCalendarId(), request.getDate())
                .orElse(createSchedule(request));
    }

    private Schedule createSchedule(ScheduleRequest request) {
        Schedule schedule = Schedule.from(request);
        return scheduleRepository.save(schedule);
    }

    public void updateIsScheduled(Long memberId, ScheduleRequest request) {
        Schedule schedule = retrieveSchedule(memberId, request);
        schedule.changeIsScheduled();

        schedule = scheduleRepository.save(schedule);
        log.info("[ScheduleService.updateIsScheduled] Success change schedule : {}", schedule);
    }

    private Calendar createCalendar(Calendar calendar, Long memberId, ScheduleRequest request) {
        CalendarRequest calendarRequest = new CalendarRequest(request.getYearMonth(), memberId);
        calendar = calendarService.createCalendar(calendarRequest);
        return calendar;
    }
}
