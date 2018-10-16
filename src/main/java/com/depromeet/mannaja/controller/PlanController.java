package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.service.plan.PlanFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    PlanFinder planFinder;

    @GetMapping("/{planId}")
    public List<Plan> getPlans(@PathVariable Long planId){
        return null;
    }
}
