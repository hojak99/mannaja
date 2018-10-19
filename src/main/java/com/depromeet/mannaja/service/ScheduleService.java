package com.depromeet.mannaja.service;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.controller.request.ScheduleRequest;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Schedule;
import com.depromeet.mannaja.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    private CalendarService calendarService;

    public ScheduleService(ScheduleRepository scheduleRepository, CalendarService calendarService) {
        this.scheduleRepository = scheduleRepository;
        this.calendarService = calendarService;
    }

    public Schedule retrieveSchedule(Long memberId, LocalDate date) {
        Calendar calendar = checkExistCalendar(memberId, date);
        Schedule schedule = findByCalenedarIdAndDate(calendar.getId(), date);

        log.info("[ScheduleService.changeIsScheduled] Success retrieve schedule : {}", schedule);
        return schedule;
    }

    private Calendar checkExistCalendar(Long memberId, LocalDate date) {
        CalendarRequest request = CalendarRequest.builder()
                .memberId(memberId)
                .yearMonth(convertYearMonth(date))
                .build();

        return calendarService.checkExistCalendar(request);
    }

    private String convertYearMonth(LocalDate localDate) {
        return localDate.getYear() + "-" + String.format("%02d", localDate.getMonthValue());
    }

    private Schedule findByCalenedarIdAndDate(Long calendarId, LocalDate date) {
        String day = String.format("%02d", date.getDayOfMonth());

        return scheduleRepository.findByCalendarIdAndDate(calendarId, day)
                .orElseThrow(() -> new IllegalArgumentException("no schedule data. day: " + day));
    }

    public void updateIsScheduled(Long memberId, LocalDate date) {
        Schedule schedule = checkExistScheduleData(memberId, date);

        schedule.changeIsScheduled();
        schedule = scheduleRepository.save(schedule);

        log.info("[ScheduleService.updateIsScheduled] Success change schedule : {}", schedule);
    }

    private Schedule checkExistScheduleData(Long memberId, LocalDate date) {
        try {
            Schedule schedule = retrieveSchedule(memberId, date);
            return schedule;
        } catch (IllegalArgumentException e) {
            Calendar calendar = checkExistCalendar(memberId, date);

            ScheduleRequest request = ScheduleRequest
                    .builder()
                    .calendarId(calendar.getId())
                    .scheduleDate(date)
                    .build();

            Schedule schedule = createSchedule(request);
            log.info("[ScheduleService.checkExistScheduleData] create schedule data, because no schedule data : {}", schedule);
            return schedule;
        }
    }

    private Schedule createSchedule(ScheduleRequest request) {
        Schedule schedule = Schedule.create(request);
        return scheduleRepository.save(schedule);
    }
}
