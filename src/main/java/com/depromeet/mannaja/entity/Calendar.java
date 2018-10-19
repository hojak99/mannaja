package com.depromeet.mannaja.entity;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Table(name = "calendar")
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "yearMonth")
    private String yearMonth;

    @CreatedDate
    @LastModifiedDate
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private List<Schedule> scheduleList;

    public static Calendar create(CalendarRequest request) {
        Calendar calendar = new Calendar();
        calendar.yearMonth = request.getYearMonth();
        calendar.memberId = request.getMemberId();

        return calendar;
    }

    public static Calendar createEmptyScheduleList(String yearMonth) {
        Calendar calendar = new Calendar();
        calendar.yearMonth = yearMonth;
        calendar.scheduleList = Collections.emptyList();

        return calendar;
    }

    public boolean isIdNull() {
        return id == null ? true : false;
    }
}
