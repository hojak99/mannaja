package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.controller.request.MemberRequest;
import com.depromeet.mannaja.controller.resposne.MemberResponse;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.service.member.MemberFinder;
import com.depromeet.mannaja.service.member.MemberRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController{
    @Autowired
    private MemberFinder memberFinder;

    @Autowired
    private MemberRegister memberRegister;

    @GetMapping("/{memberId}")
    public MemberResponse getMember(@PathVariable Long memberId){
        Member member = memberFinder.getMember(memberId);

        return MemberResponse.from(member);
    }

    @PostMapping("/")
    public void createMember(@RequestBody MemberRequest request) {
        memberRegister.createMember(request);
    }
}
