package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public void addNewMember(@Valid @RequestBody Member member) {
        memberService.addNewMember(member);
    }

    @DeleteMapping("/member/{memberId}")
    public void cancelMembership(@PathVariable String memberId) {
        memberService.cancelMembership(memberId);
    }

    @PutMapping("/member/prohibit")
    public String prohibitMember(@RequestParam String memberId) {
        return memberService.prohibitMember(memberId);
    }

    @PutMapping("/member/{memberId}")
    public Member editMemberInformation(@PathVariable String memberId, @RequestBody Member member) {
        return memberService.editMemberInformation(memberId, member);
    }

    @GetMapping("/member")
    public List<Member> getAllMembers() {
        return memberService.getAllMember();
    }

}
