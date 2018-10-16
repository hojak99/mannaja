package com.depromeet.mannaja.service.plan;

import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanFinder {
    @Autowired
    PlanRepository planRepository;

    public Optional<Plan> getPlan(Long planId){
        return planRepository.findById(planId);
    }
}
