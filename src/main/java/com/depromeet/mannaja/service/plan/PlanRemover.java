package com.depromeet.mannaja.service.plan;

import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanRemover {
    @Autowired
    PlanRepository planRepository;

    public void removePlan(Long planId) {
        planRepository.deleteById(planId);
    }

    public void leavePlan(Long planId, Long memberId){
        Plan plan = planRepository.findById(planId).get();
        plan.removeMember(memberId);
        planRepository.save(plan);
    }
}
