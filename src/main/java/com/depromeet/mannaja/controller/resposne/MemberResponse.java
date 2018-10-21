package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.entity.Plan;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemberResponse {
    private Long id;
    private String uuid;
    private String name;

    public static MemberResponse from(Member member) {
        MemberResponse response = new MemberResponse();
        response.id = member.getId();
        response.uuid = member.getUuid();
        response.name = member.getName();

        return response;
    }
}
