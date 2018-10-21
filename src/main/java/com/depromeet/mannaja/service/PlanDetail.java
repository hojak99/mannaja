package com.depromeet.mannaja.service;

import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.entity.Plan;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class PlanDetail {
    private Plan plan;

    private List<LocalDate> allValidDate;

    //멤버별 validDate
    private  Map<Member,List<LocalDate>>  dateMapByMemberId;
}
