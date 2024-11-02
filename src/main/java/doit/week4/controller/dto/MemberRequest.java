package doit.week4.controller.dto;

import lombok.Data;

@Data
public class MemberRequest {

    private String email;

    private String name;

    private String password;

    private String phone;
}
