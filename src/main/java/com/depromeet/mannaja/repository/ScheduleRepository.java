package com.depromeet.mannaja.repository;

import com.depromeet.mannaja.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<List<Schedule>> findAllByCalendarIdAndDateIn(Long calendarId, List<String> date);
}
