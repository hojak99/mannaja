package com.depromeet.mannaja.repository;

import com.depromeet.mannaja.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByCalendarIdAndDate(Long calendarId, String date);
}
