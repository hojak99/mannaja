package com.depromeet.mannaja.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="member", schema = "mannaja")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String name;

    private LocalDateTime lastLogIn;

    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "personalPlan",
            joinColumns = @JoinColumn(name = "planId"),
            inverseJoinColumns = @JoinColumn(name = "memberId"))
    private List<Plan> planList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId")
    private List<Calendar> calendarList;
}
