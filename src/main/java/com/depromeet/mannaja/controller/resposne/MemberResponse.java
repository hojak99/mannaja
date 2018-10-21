package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.entity.Plan;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse {
    private Long id;
    private String uuid;
    private String name;
    private List<CalendarResponse> calendarList;

    public static MemberResponse from(Member member) {
        MemberResponse response = new MemberResponse();
        response.id = member.getId();
        response.uuid = member.getUuid();
        response.name = member.getName();
        if (!CollectionUtils.isEmpty(member.getCalendarList())) {
            response.calendarList = member.getCalendarList()
                    .stream()
                    .map(CalendarResponse::from)
                    .collect(Collectors.toList());
        }

        return response;
    }
}
