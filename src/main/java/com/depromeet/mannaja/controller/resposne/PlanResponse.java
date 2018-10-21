package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.entity.Schedule;
import com.depromeet.mannaja.service.PlanDetail;
import lombok.Data;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlanResponse {
    private Long id;
    private String name;
    private String planYearMonth;
    private LocalDate settleDate;
    private List<MemberResponse> memberList;

    public static PlanResponse from(Plan plan) {
        PlanResponse response = new PlanResponse();
        response.id = plan.getId();
        response.name = plan.getName();
        response.planYearMonth = plan.getPlanYearMonth();
        response.settleDate = plan.getSettleDate();
        response.memberList = plan.getMemberList()
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());

        return response;
    }
}
