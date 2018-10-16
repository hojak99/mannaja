package com.depromeet.mannaja.entity;

import com.depromeet.mannaja.controller.request.CalendarRequest;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "calendar")
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Calendar {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "yearMonth")
    private LocalDate yearMonth;

    @CreatedDate
    @LastModifiedDate
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "calendarId")
    private List<Schedule> scheduleList;

    public static Calendar from(CalendarRequest request) {
        return Calendar
                .builder()
                .yearMonth(request.getYearMonth())
                .memberId(request.getMemberId())
                .build();
    }
}
