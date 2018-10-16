package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.ScheduleRequest;
import com.depromeet.mannaja.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PatchMapping("/schedule/{no}")
    public void patchSchedule(
            @PathVariable(name = "no") Long memberId,
            @RequestBody ScheduleRequest request) {

        scheduleService.updateIsScheduled(memberId, request);
    }
}
