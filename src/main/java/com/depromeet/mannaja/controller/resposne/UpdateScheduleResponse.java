package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UpdateScheduleResponse {
    private Long id;
    private String date;
    private boolean scheduled;

    public static UpdateScheduleResponse from(Schedule schedule){
        UpdateScheduleResponse response = new UpdateScheduleResponse();
        response.id = schedule.getId();
        response.date = schedule.getDate();
        response.scheduled = schedule.isScheduled();

        return response;
    }
}
