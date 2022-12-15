package com.team9.boardapi.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

    //관리자 인지 아닌지 확인
    private boolean admin = false;

    //관리자일때 넣어줘야하는 토큰값
    private String adminToken = "";
}
