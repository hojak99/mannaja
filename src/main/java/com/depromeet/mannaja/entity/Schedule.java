package com.depromeet.mannaja.entity;

import com.depromeet.mannaja.controller.request.ScheduleRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "schedule")
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "calendarId")
    private Long calendarId;

    @Column(name = "date")
    private LocalTime date;

    @Column(name = "isScheduled")
    private boolean isScheduled;

    @CreatedDate
    @LastModifiedDate
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    public void changeIsScheduled() {
        this.isScheduled = !this.isScheduled;
    }

    public static Schedule from(ScheduleRequest request){
        Schedule schedule = new Schedule();
        schedule.date = request.getDate();

        return schedule;
    }
}
