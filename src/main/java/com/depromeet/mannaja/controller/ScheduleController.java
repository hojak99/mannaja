package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.ScheduleRequest;
import com.depromeet.mannaja.controller.resposne.ScheduleResponse;
import com.depromeet.mannaja.entity.Schedule;
import com.depromeet.mannaja.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ApiOperation(value = "자신의 스케줄 상태 변경", notes = "자신의 스케줄에서 약속 유무 설정. " +
            "`date` 에는 `yyyy-MM-dd` 형태. 스케줄 정보가 없을 시 스케줄 생성 후 상태 값 변경을 자동으로 차리")
    @PatchMapping("/schedule/{memberId}")
    public void patchSchedule(
            @PathVariable(name = "memberId") Long memberId,
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        scheduleService.updateIsScheduled(memberId, date);
    }

    @ApiOperation(value = "자신의 특정 스케줄 조회", notes = "자신의 특정 스케줄을 조회함. `data` 는 `yyyy-MM-dd` 형태. 스케줄 존재하지 않을 시 exception 뱉음.")
    @GetMapping("/schedule/{memberId}")
    public ScheduleResponse retrieveSchedule(
            @PathVariable(name = "memberId") Long memberId,
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        Schedule schedule = scheduleService.retrieveSchedule(memberId, date);
        return ScheduleResponse.from(schedule.getCalendar().getYearMonth(), schedule);
    }
}
