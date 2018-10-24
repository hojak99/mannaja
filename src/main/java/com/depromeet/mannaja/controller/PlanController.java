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
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/{memberId}")
    public PlanListResponse getPlanList(
            @PathVariable(value = "memberId") Long memberId) {
        return PlanListResponse.from(planFinder.getPlanList(memberId));
    }

    @ApiOperation(value = "약속 상세정보", notes = "약속 상세 정보")
    @GetMapping("/detail/{planId}")
    public PlanDetailResponse getPlanDetail(@PathVariable Long planId) {
        return PlanDetailResponse.from(planFinder.findPlanDetail(planId));

    }

    @ApiOperation(value = "약속 날짜 정하기", notes = "약속 날짜 정하는 api. settleDate 는 yyyy-MM-dd 입니다.")
    @PatchMapping("/")
    public void setPlanSettleDate(
            @RequestBody PlanRequest request) {
        planModifier.setSettleDate(request);
    }

    @ApiOperation(value = "약속 생성하기", notes = "약속 생성하는 api. plateDate 는 yyyy-MM 으로 보내셔야 합니다." +
            "planDate 가 무슨 달에 약속 잡을지! 이거고 settleDate 가 yyyy-MM-dd 로 약속 잡은 날짜! 입니다")
    @PostMapping("/create")
    public void createPlan(@Valid @RequestBody CreatePlan createPlan) {
        if(createPlan.getPlanDate().length() != 7) {
            throw new IllegalArgumentException("plateDate must be yyyy-MM");
        }

        planRegister.create(createPlan);
    }

    @DeleteMapping("/delete/{planId}")
    public void deletePlan(@PathVariable Long planId) {
        planRemover.removePlan(planId);
    }

    @PostMapping("/leave/{planId}/{memberId}")
    public void leavePlan(@PathVariable Long planId, @PathVariable Long memberId) {
        planRemover.leavePlan(planId, memberId);
    }
}
