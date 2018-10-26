package com.depromeet.mannaja.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@ToString
@Entity
@Table(name="plan", schema = "mannaja")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Plan{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "plan_year_month")
    private String planYearMonth;

    @Setter
    @Column(name = "settle_date")
    private LocalDate settleDate;

    @CreatedDate
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personal_plan",
        joinColumns = @JoinColumn(name = "plan_id"),
        inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<Member> memberList;

    @Column(name = "invitation_url")
    private String invitationUrl;

    public void addMember(Member member){
        memberList.add(member);
    }

    public void removeMember(Long memberId) {
        for(Iterator<Member> it = memberList.iterator(); it.hasNext();){
            Member member1 = it.next();
            if(memberId.equals(member1.getId())){
                it.remove();
                break;
            }
        }
    }
}
