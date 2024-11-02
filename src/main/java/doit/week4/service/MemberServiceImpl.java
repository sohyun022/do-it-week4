package doit.week4.service;

import doit.week4.controller.dto.MemberRequest;
import doit.week4.controller.dto.MemberResponse;
import doit.week4.repository.Member;
import doit.week4.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<MemberResponse> findAllMembers() {

        List<MemberResponse> members = new ArrayList<>();
        memberRepository.findAll().forEach(member -> members
                .add(MemberResponse.builder()
                        .phone(member.getPhone())
                        .name(member.getName())
                        .email(member.getEmail())
                        .active(member.isActive())
                        .build()));

        return members;
    }

    @Override
    public void joinMember(MemberRequest memberRequest) {

        if (memberRepository.existsByEmail(memberRequest.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());

        Member member = Member.builder()
                .email(memberRequest.getEmail())
                .password(encodedPassword)
                .name(memberRequest.getName())
                .phone(memberRequest.getPhone())
                .active(true)
                .build();


        memberRepository.save(member);

    }

    @Override
    public MemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if(!member.isActive()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        return MemberResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .build();

    }

    @Override
    public void updateMember(Long memberId, MemberRequest memberRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if(!member.isActive()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        member.updateMemberInfo(memberRequest);

        memberRepository.save(member);

    }

    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if(!member.isActive()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        member.deleteMember();

        memberRepository.save(member);

    }

    @Override
    public void restoreMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if(member.isActive()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        member.restoreMember();

        memberRepository.save(member);

    }


}
