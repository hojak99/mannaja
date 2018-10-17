package com.depromeet.mannaja.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name="plan", schema = "mannaja")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Plan{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate settleDate;

    private LocalDateTime modifiedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personalPlan",
        joinColumns = @JoinColumn(name = "memberId"),
        inverseJoinColumns = @JoinColumn(name = "planId"))
    private List<Member> memberList;

    public void addMember(Member member){
        memberList.add(member);
//        Map<LocalDate,Integer> invalidMap = memberList.stream()
//                .collect(Collectors.toMap())
//TODO 캘린더생기면 업데이트
    }
}
