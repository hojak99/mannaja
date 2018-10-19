package com.depromeet.mannaja.entity;

import com.depromeet.mannaja.controller.request.ScheduleRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ToString(exclude = {"calendar"})
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "schedule")
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calendar_id")
    private Long calendarId;

    @Column(name = "date")
    private String date;

    @Column(name = "is_scheduled")
    private boolean isScheduled;

    @CreatedDate
    @LastModifiedDate
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", insertable = false, updatable = false)
    private Calendar calendar;

    public void changeIsScheduled() {
        this.isScheduled = !this.isScheduled;
    }

    public static Schedule create(ScheduleRequest request){
        Schedule schedule = new Schedule();
        schedule.calendarId = request.getCalendarId();
        schedule.date = String.valueOf(request.getScheduleDate().getDayOfMonth());

        return schedule;
    }
}
