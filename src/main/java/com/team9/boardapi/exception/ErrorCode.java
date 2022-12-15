package com.team9.boardapi.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    SIZE("ERROR_SIZE","조건에 맞는 글자수가 아닙니다."),
    PATTERN("ERROR_PATTERN","조건에 맞지 않습니다.");

    private String code;

    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
