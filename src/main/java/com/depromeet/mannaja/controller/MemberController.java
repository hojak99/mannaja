package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.resposne.MemberResponse;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.service.member.MemberFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController{
    @Autowired
    private MemberFinder memberFinder;

    @GetMapping("/{memberId}")
    public MemberResponse getMember(@PathVariable Long memberId){
        Member member = memberFinder.getMember(memberId);

        log.info("{}", member.getId());
        return MemberResponse.from(member);
    }
}
