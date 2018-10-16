package com.depromeet.mannaja.repository;


import com.depromeet.mannaja.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Optional<Calendar> findByMemberIdAndYearMonth(Long memberId, LocalDate localDate);
}
