package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void addNewMember(Member member) {
        boolean exist = memberRepository.existsById(member.getEmail());
        if (exist) {
            throw new IllegalStateException("The email has taken!");
        }
        memberRepository.save(member);
    }

    public void cancelMembership(String memberId) {
        boolean exist = memberRepository.existsById(memberId);
        if (!exist) {
            throw new IllegalStateException("The member id " + memberId + "does not exist!");
        }
        memberRepository.deleteById(memberId);
    }

    public String prohibitMember(String memberId) {
        Member memberFound = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("The member id " + memberId + "does not exist!"));

        if (memberFound.isProhibited()) {
            return "The member is already forbidden from using the library service any longer!";
        } else {
            memberFound.setProhibited(true);
            memberRepository.save(memberFound);
            return "The user is prohibited successfully!";
        }
    }

    public Member editMemberInformation(String memberId, Member member) {
        Member memberFound = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("The member id " + memberId + "does not exist!"));

        if (member.getEmail() != null && !Objects.equals(member.getEmail(), "")) {
            Optional<Member> duplicatedEmail = memberRepository.findByEmail(member.getEmail());
            if (duplicatedEmail.isPresent()) {
                throw new IllegalStateException("The email has taken!");
            }
            memberFound.setEmail(member.getEmail());
        }
        if (member.getName() != null && !Objects.equals(member.getName(), "")) {
            memberFound.setName(member.getName());
        }
        if (member.getDob() != null) {
            memberFound.setDob(member.getDob());
        }
        if (member.getPhone() != null && !Objects.equals(member.getPhone(), "")) {
            memberFound.setPhone(member.getPhone());
        }
        if (member.getAddress() != null && !Objects.equals(member.getAddress(), "")) {
            memberFound.setAddress(member.getAddress());
        }
        memberRepository.save(memberFound);
        return memberFound;
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

}
