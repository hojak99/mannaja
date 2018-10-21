package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.CreatePlan;
import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.service.PlanDetail;
import com.depromeet.mannaja.service.plan.PlanFinder;
import com.depromeet.mannaja.service.plan.PlanRegister;
import com.depromeet.mannaja.service.plan.PlanRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanFinder planFinder;

    @Autowired
    private PlanRemover planRemover;

    @Autowired
    private PlanRegister planRegister;

    @GetMapping("/{planId}")
    public Plan getPlan(@PathVariable Long planId){
        return planFinder.getPlan(planId);
    }

    @GetMapping("/detail/{planId}")
    public PlanDetail getPlanDetail(@PathVariable Long planId){
        return planFinder.findPlanDetail(planId);

    }

    @PostMapping("/create")
    public boolean createPlan(@Valid @RequestBody CreatePlan createPlan){
        planRegister.create(createPlan);
        return true;
    }

    @GetMapping("/delete/{planId}")
    public void deletePlan(@PathVariable Long planId){
        planRemover.removePlan(planId);
    }

    @PostMapping("/leave/{planId}/{memberId}")
    public void leavePlan(@PathVariable Long planId, @PathVariable Long memberId){
        planRemover.leavePlan(planId,memberId);
    }
}
