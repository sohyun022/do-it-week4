package doit.week4.service;

import doit.week4.controller.dto.MemberRequest;
import doit.week4.controller.dto.MemberResponse;

import java.util.List;

public interface MemberService {

    List<MemberResponse> findAllMembers();
    void joinMember(MemberRequest memberRequest);
    MemberResponse getMember(Long memberId);
    void updateMember(Long memberId, MemberRequest memberRequest);
    void deleteMember(Long memberId);
    void restoreMember(Long memberId);

}
