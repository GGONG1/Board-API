package com.team9.boardapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequestDto {

    @Size(min = 4, max = 10, message = "id는 4자 이상 10자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{4,10}$", message = "id는 소문자와 숫자 조합 4자리에서 10자리입니다.")
    private String username;

    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "비밀번호는 소문자, 대문자, 숫자, 특수문자(!@#$%^&+=) 조합 8자리에서 15자리입니다.")
    private String password;

    //관리자 인지 아닌지 확인
    private boolean admin = false;

    //관리자일때 넣어줘야하는 토큰값
    private String adminToken = "";

}
