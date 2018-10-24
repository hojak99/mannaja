package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.entity.Plan;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlanListResponse {
    private Long id;
    private String uuid;
    private String name;
    private List<PlanResponse> planList;

    public static PlanListResponse from(Member member) {
        PlanListResponse planListResponse = new PlanListResponse();
        planListResponse.id = member.getId();
        planListResponse.name = member.getName();
        planListResponse.planList = member.getPlanList()
                .stream()
                .map(PlanResponse::from)
                .collect(Collectors.toList());

        for(PlanResponse planResponse : planListResponse.planList) {
            for(int i = 0; i <planResponse.getMemberList().size(); ++i) {
                MemberResponse memberResponse = planResponse.getMemberList().get(i);

                if(planListResponse.id.equals(memberResponse.getId())){
                    planResponse.getMemberList().remove(i);
                }
            }
        }

        return planListResponse;
    }
}
