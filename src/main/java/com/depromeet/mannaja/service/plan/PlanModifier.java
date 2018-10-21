package com.depromeet.mannaja.service.plan;

import com.depromeet.mannaja.controller.request.PlanRequest;
import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanModifier {

    @Autowired
    private PlanFinder planFinder;

    @Autowired
    private PlanRepository planRepository;

    public void setSettleDate(PlanRequest request) {
        Plan plan = planFinder.getPlan(request.getPlanId());
        plan.setSettleDate(request.getSettleDate());

        planRepository.save(plan);
    }
}
