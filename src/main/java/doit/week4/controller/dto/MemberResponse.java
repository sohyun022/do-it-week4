package doit.week4.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponse {

    private String name;
    private String email;
    private String phone;
    private boolean active;

}
