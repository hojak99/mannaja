package com.depromeet.mannaja.service.plan;

import com.depromeet.mannaja.controller.request.CreatePlan;
import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.entity.Schedule;
import com.depromeet.mannaja.repository.CalendarRepository;
import com.depromeet.mannaja.repository.PlanRepository;
import com.depromeet.mannaja.service.member.MemberFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Slf4j
@Service
public class PlanRegister {
    @Autowired
    PlanRepository planRepository;

    @Autowired
    MemberFinder memberFinder;

    @Autowired
    CalendarRepository calendarRepository;

    public Plan create(CreatePlan createPlan){
        Plan plan = Plan.builder()
                .name(createPlan.getName())
                .planYearMonth(createPlan.getPlanDate())
                .memberList(new ArrayList<>())
                .build();

        plan.addMember(memberFinder.getMember(createPlan.getMemberId()));
        plan = planRepository.save(plan);

        log.info("[PlanRegister.create] success create plan : {}", plan);
        return plan;
    }

    public Map<Long, Boolean> getDateBooleanMap(Plan plan){
        Map<Long, Boolean> dateBooleanMap = new HashMap<>();

        List<Member> memberList = plan.getMemberList();
        for(Member member : memberList){
            Optional<Calendar> calendar = calendarRepository.findByMemberIdAndYearMonth(member.getId(), plan.getPlanYearMonth());
            if(calendar.isPresent()){
                Calendar calendar1 = calendar.get();
                List<Schedule> schedules = calendar1.getScheduleList();
                schedules.stream()
                        .filter(schedule -> !schedule.isScheduled())
                        .forEach(schedule -> dateBooleanMap.put(Long.parseLong(schedule.getDate()),true));
            }
        }

        return dateBooleanMap;
    }

    private void updateSettleDate(Plan plan){
        Map<Long, Boolean> dateBooleanMap = getDateBooleanMap(plan);
        LocalDate startDate = YearMonth.parse(plan.getPlanYearMonth()).atDay(1);
        LocalDate endDate = startDate.plusMonths(1);
        while(!startDate.isAfter(endDate)){
            if(dateBooleanMap.containsKey(startDate)){
                plan.setSettleDate(startDate);
                break;
            }
            startDate = startDate.plusDays(1);
        }

    }
}
