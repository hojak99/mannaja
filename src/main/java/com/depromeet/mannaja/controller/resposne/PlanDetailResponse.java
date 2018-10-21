package com.depromeet.mannaja.controller.resposne;

import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.service.PlanDetail;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class PlanDetailResponse {

    private PlanResponse plan;
    private List<LocalDate> allValidDateList;
    private Map<MemberResponse, List<LocalDate>> dateMapByMemberId;

    public static PlanDetailResponse from(PlanDetail planDetail) {
        PlanDetailResponse response = new PlanDetailResponse();
        response.allValidDateList = planDetail.getAllValidDate();
//        response.dateMapByMemberId = planDetail.getDateMapByMemberId();
        response.plan = PlanResponse.from(planDetail.getPlan());

        return response;
    }
}
