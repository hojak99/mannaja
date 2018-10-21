package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Plan;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlanListResponse {
    private Long id;
    private String name;
    private String planYearMonth;
    private LocalDate settleDate;
    private List<MemberResponse> memberResponseList;

    public static PlanListResponse from(Plan plan) {
        PlanListResponse planListResponse = new PlanListResponse();
        planListResponse.id = plan.getId();
        planListResponse.name = plan.getName();
        planListResponse.planYearMonth = plan.getPlanYearMonth();
        planListResponse.settleDate = plan.getSettleDate();
        planListResponse.memberResponseList = plan.getMemberList()
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());

        return planListResponse;
    }
}
