package com.depromeet.mannaja.repository;

import com.depromeet.mannaja.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
