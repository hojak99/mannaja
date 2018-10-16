package com.depromeet.mannaja.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    private LocalDate settleDate;

    private LocalDateTime modifiedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personalPlan",
        joinColumns = @JoinColumn(name = "memberId"),
        inverseJoinColumns = @JoinColumn(name = "planId"))
    private List<Member> memberList;
}
