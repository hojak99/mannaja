package com.depromeet.mannaja.service.plan;

import com.depromeet.mannaja.controller.request.CreatePlan;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.repository.PlanRepository;
import com.depromeet.mannaja.service.member.MemberFinder;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PlanRegister {
    @Autowired
    PlanRepository planRepository;

    @Autowired
    MemberFinder memberFinder;

    public Plan create(CreatePlan createPlan){
        Plan plan = Plan.builder()
                .name(createPlan.getName())
                .settleDate(LocalDate.parse(createPlan.getPlanDate()))
                .build();
        plan.addMember(memberFinder.getMember(createPlan.getMemberId()).get());

        return planRepository.save(plan);
    }
}
