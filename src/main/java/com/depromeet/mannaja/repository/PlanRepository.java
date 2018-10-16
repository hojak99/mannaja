package com.depromeet.mannaja.repository;

import com.depromeet.mannaja.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PlanRepository extends JpaRepository<Plan,Long>, QuerydslPredicateExecutor<Plan> {
}
