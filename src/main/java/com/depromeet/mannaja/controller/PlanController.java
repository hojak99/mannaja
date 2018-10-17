package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.CreatePlan;
import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.service.plan.PlanFinder;
import com.depromeet.mannaja.service.plan.PlanRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanFinder planFinder;

    @Autowired
    private PlanRegister planRegister;

    @GetMapping("/{planId}")
    public Plan getPlans(@PathVariable Long planId){
        return null;
    }

    @PostMapping("/create")
    public boolean createPlan(@RequestBody CreatePlan createPlan){

        return true;
    }
}
