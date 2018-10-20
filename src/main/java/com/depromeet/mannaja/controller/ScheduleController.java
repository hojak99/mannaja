package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.ScheduleRequest;
import com.depromeet.mannaja.controller.resposne.ScheduleResponse;
import com.depromeet.mannaja.entity.Schedule;
import com.depromeet.mannaja.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            @RequestBody List<ScheduleRequest> scheduleRequestList) {

        scheduleService.updateIsScheduled(memberId,
                scheduleRequestList
                        .stream()
                        .map(request -> request.getScheduleDate()).collect(Collectors.toList()));
    }

    @ApiOperation(value = "자신의 특정 스케줄 조회", notes = "자신의 특정 스케줄을 조회함. `data` 는 `yyyy-MM-dd` 형태. 스케줄 존재하지 않을 시 exception 뱉음.")
    @GetMapping("/schedule/{memberId}")
    public List<ScheduleResponse> retrieveSchedule(
            @PathVariable(name = "memberId") Long memberId,
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        List<Schedule> scheduleList = scheduleService.retrieveScheduleList(memberId, Arrays.asList(date));
        return scheduleList
                .stream()
                .map(schedule -> ScheduleResponse.from(schedule.getCalendar().getYearMonth(), schedule)).collect(Collectors.toList());
    }
}
