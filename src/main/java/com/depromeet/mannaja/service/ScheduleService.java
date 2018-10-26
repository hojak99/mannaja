package com.depromeet.mannaja.service;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import com.depromeet.mannaja.controller.request.ScheduleRequest;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Schedule;
import com.depromeet.mannaja.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    private CalendarService calendarService;

    public ScheduleService(ScheduleRepository scheduleRepository, CalendarService calendarService) {
        this.scheduleRepository = scheduleRepository;
        this.calendarService = calendarService;
    }

    public List<Schedule> retrieveScheduleList(Long memberId, List<LocalDate> dateList) {
        Calendar calendar = checkExistCalendar(memberId, dateList);
        List<Schedule> scheduleList = findByCalenedarIdAndDate(calendar.getId(), dateList);

        log.info("[ScheduleService.changeIsScheduled] Success retrieve schedule : {}", scheduleList);
        return scheduleList;
    }

    private Calendar checkExistCalendar(Long memberId, List<LocalDate> dateList) {
        CalendarRequest request = CalendarRequest.builder()
                .memberId(memberId)
                .yearMonth(YearMonth.parse(convertYearMonth(dateList.get(0))))       // 어짜피 달만 알면 됨.
                .build();

        return calendarService.checkExistCalendar(request);
    }

    private String convertYearMonth(LocalDate localDate) {
        return localDate.getYear() + "-" + String.format("%02d", localDate.getMonthValue());
    }

    private List<Schedule> findByCalenedarIdAndDate(Long calendarId, List<LocalDate> dateList) {

        List<String> dayList = new ArrayList<>();
        dateList.forEach(localDate -> {
            dayList.add(String.format("%02d", localDate.getDayOfMonth()));
        });

        return scheduleRepository.findAllByCalendarIdAndDateIn(calendarId, dayList)
                .orElseThrow(() -> new IllegalArgumentException("no schedule data. day: " + dayList));
    }

    public List<Schedule> updateIsScheduled(Long memberId, List<LocalDate> date) {
        List<Schedule> scheduleList = checkExistScheduleData(memberId, date);

        scheduleList.stream().forEach(schedule -> schedule.changeIsScheduled());
        scheduleList = scheduleRepository.saveAll(scheduleList);

        log.info("[ScheduleService.updateIsScheduled] Success change schedule : {}", scheduleList);
        return scheduleList;
    }

    private List<Schedule> checkExistScheduleData(Long memberId, List<LocalDate> dateList) {
        try {
            List<Schedule> scheduleList = retrieveScheduleList(memberId, dateList);
            return scheduleList;
        } catch (IllegalArgumentException e) {
            Calendar calendar = checkExistCalendar(memberId, dateList);

            List<ScheduleRequest> requestList = new ArrayList<>();

            dateList.stream().forEach(localDate -> {
                ScheduleRequest request = ScheduleRequest
                        .builder()
                        .calendarId(calendar.getId())
                        .scheduleDate(localDate)
                        .build();

                requestList.add(request);
            });

            List<Schedule> scheduleList = createSchedule(requestList);
            log.info("[ScheduleService.checkExistScheduleData] create schedule data, because no schedule data : {}", scheduleList);
            return scheduleList;
        }
    }

    private List<Schedule> createSchedule(List<ScheduleRequest> requestList) {
        List<Schedule> scheduleList = new ArrayList<>();
        requestList.stream().forEach(request -> {
            scheduleList.add(Schedule.create(request));
        });
        return scheduleRepository.saveAll(scheduleList);
    }
}
