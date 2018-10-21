package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.CreatePlan;
import com.depromeet.mannaja.controller.request.PlanRequest;
import com.depromeet.mannaja.controller.resposne.PlanDetailResponse;
import com.depromeet.mannaja.controller.resposne.PlanListResponse;
import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.service.PlanDetail;
import com.depromeet.mannaja.service.plan.PlanFinder;
import com.depromeet.mannaja.service.plan.PlanModifier;
import com.depromeet.mannaja.service.plan.PlanRegister;
import com.depromeet.mannaja.service.plan.PlanRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanFinder planFinder;

    @Autowired
    private PlanRemover planRemover;

    @Autowired
    private PlanRegister planRegister;

    @Autowired
    private PlanModifier planModifier;

    @GetMapping("/{planId}")
    public Plan getPlan(@PathVariable Long planId) {
        return planFinder.getPlan(planId);
    }

    @GetMapping("/")
    public Page<PlanListResponse> getPlanList(@PageableDefault Pageable pageable) {
        return planFinder.getPlanList(pageable)
                .map(PlanListResponse::from);
    }

    @GetMapping("/detail/{planId}")
    public PlanDetailResponse getPlanDetail(@PathVariable Long planId) {
        return PlanDetailResponse.from(planFinder.findPlanDetail(planId));

    }

    @PatchMapping("/")
    public void setPlanSettleDate(
            @RequestBody PlanRequest request) {
        planModifier.setSettleDate(request);
    }

    @PostMapping("/create")
    public void createPlan(@Valid @RequestBody CreatePlan createPlan) {
        planRegister.create(createPlan);
    }

    @GetMapping("/delete/{planId}")
    public void deletePlan(@PathVariable Long planId) {
        planRemover.removePlan(planId);
    }

    @PostMapping("/leave/{planId}/{memberId}")
    public void leavePlan(@PathVariable Long planId, @PathVariable Long memberId) {
        planRemover.leavePlan(planId, memberId);
    }
}
