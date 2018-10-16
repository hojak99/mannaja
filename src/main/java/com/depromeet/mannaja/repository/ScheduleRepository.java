package com.depromeet.mannaja.repository;

import com.depromeet.mannaja.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Calendar, Long> {
}
