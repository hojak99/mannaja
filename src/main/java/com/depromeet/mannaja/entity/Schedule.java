package com.depromeet.mannaja.entity;

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
@Builder
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

}
