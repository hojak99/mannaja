package com.depromeet.mannaja.service.plan;

import com.depromeet.mannaja.entity.Calendar;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.entity.Plan;
import com.depromeet.mannaja.repository.CalendarRepository;
import com.depromeet.mannaja.repository.PlanRepository;
import com.depromeet.mannaja.service.PlanDetail;
import com.depromeet.mannaja.service.member.MemberFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanFinder {
    @Autowired
    PlanRegister planRegister;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    MemberFinder memberFinder;

    public Member getPlanList(Long memberId) {
        return memberFinder.getMember(memberId);
    }

    public Plan getPlan(Long planId) {
        return planRepository.findById(planId).get();
    }

    public PlanDetail findPlanDetail(Long planId) {
        Plan plan = getPlan(planId);
        PlanDetail planDetail = PlanDetail.builder()
                .plan(plan)
                .allValidDate(getValidDateList(plan))
                .dateMapByMemberId(getMemberMap(plan))
                .build();

        return planDetail;
    }

    private Map<Member, List<LocalDate>> getMemberMap(Plan plan) {
        Map<Member, List<LocalDate>> dateMapByMember = new HashMap<>();

        for (Member member : plan.getMemberList()) {
            Optional<Calendar> calendar = calendarRepository.findByMemberIdAndYearMonth(member.getId(), plan.getPlanYearMonth());
            if (calendar.isPresent()) {
                List<LocalDate> localDates = calendar.get().getScheduleList()
                        .stream()
                        .filter(schedule -> !schedule.isScheduled())
                        .map((schedule -> YearMonth.parse(plan.getPlanYearMonth()).atDay(Integer.valueOf(schedule.getDate()))))
                        .collect(Collectors.toList());
                dateMapByMember.put(member, localDates);
            }
        }
        return dateMapByMember;
    }

    private List<LocalDate> getValidDateList(Plan plan) {
        List<LocalDate> validDateList = new ArrayList<>();

        Map<Long, Boolean> dateBooleanMap = planRegister.getDateBooleanMap(plan);

        LocalDate startDate = YearMonth.parse(plan.getPlanYearMonth()).atDay(1);
        LocalDate endDate = startDate.plusMonths(1);

        while (!startDate.isAfter(endDate)) {
            Long day = Long.parseLong(String.format("%02d", startDate.getDayOfMonth()));

            if (dateBooleanMap.get(day) != null) {
                validDateList.add(startDate);
            }
            startDate = startDate.plusDays(1);
        }
        return validDateList;
    }
}
