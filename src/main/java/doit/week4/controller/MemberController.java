package doit.week4.controller;

import doit.week4.controller.dto.MemberRequest;
import doit.week4.controller.dto.MemberResponse;
import doit.week4.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member/")

@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<?> getMembers() {

        List<MemberResponse> members = memberService.findAllMembers();
        return ResponseEntity.ok(members);
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody MemberRequest memberRequest) {

        memberService.joinMember(memberRequest);
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @GetMapping("/{member_id}")
    public ResponseEntity<?> getMember(@PathVariable("member_id") Long memberId) {

        MemberResponse memberResponse = memberService.getMember(memberId);
        return ResponseEntity.ok("회원 정보 :\n" + memberResponse);
    }

    @PostMapping("/{member_id}/update")
    public ResponseEntity<?> updateMember(@PathVariable("member_id") Long memberId, @RequestBody MemberRequest memberRequest){

            memberService.updateMember(memberId, memberRequest);
            return ResponseEntity.ok("회원 정보 변경 완료");
    }

    @PostMapping("/{member_id}/delete")
    public ResponseEntity<?> deleteMember(@PathVariable("member_id") Long memberId) {

        memberService.deleteMember(memberId);
        return ResponseEntity.ok("회원 삭제 완료");
    }

    @PostMapping("/{member_id}/restore")
    public ResponseEntity<?> restoreMember(@PathVariable("member_id") Long memberId) {

        memberService.restoreMember(memberId);
        return ResponseEntity.ok("회원 복구 완료");
    }

}