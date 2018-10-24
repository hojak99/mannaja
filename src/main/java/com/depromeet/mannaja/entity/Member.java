package com.depromeet.mannaja.entity;

import com.depromeet.mannaja.controller.request.MemberRequest;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@ToString(exclude = {"planList", "calendarList"})
@Entity
@Table(name="member", schema = "mannaja")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @LastModifiedDate
    @Column(name = "last_login")
    private LocalDateTime lastLogIn;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personal_plan",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<Plan> planList;

    @BatchSize(size = 10)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private List<Calendar> calendarList;

    public static Member create(MemberRequest request) {
        Member member = new Member();
        member.name = request.getName();
        member.uuid = request.getUuid();

        return member;
    }
}
