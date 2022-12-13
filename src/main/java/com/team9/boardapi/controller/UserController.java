package com.team9.boardapi.controller;

import com.team9.boardapi.dto.LoginRequestDto;
import com.team9.boardapi.dto.SignupRequestDto;
import com.team9.boardapi.dto.UserRequestDto;
import com.team9.boardapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "회원가입 완료"; // dto로 변환해보자
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return "로그인 성공"; // dto로 변환해보자
    }
}
