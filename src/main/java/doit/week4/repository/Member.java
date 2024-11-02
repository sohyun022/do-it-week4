package doit.week4.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import doit.week4.controller.dto.MemberRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Email
    @NotBlank
    private String email;

    private String name;

    @JsonIgnore
    @NotBlank
    private String password;

    private String phone;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean active;

    public void updateMemberInfo(MemberRequest memberRequest){

        if(memberRequest.getName() != null){
            this.name = memberRequest.getName();
        }

        if(memberRequest.getPassword() != null){
            this.password = memberRequest.getPassword();
        }

        if(memberRequest.getPhone() != null){
            this.phone = memberRequest.getPhone();
        }

    }

    public void deleteMember(){
        this.active = false;
    }

    public void restoreMember(){
        this.active = true;
    }

}
