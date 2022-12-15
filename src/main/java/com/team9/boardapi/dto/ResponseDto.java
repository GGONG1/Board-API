package com.team9.boardapi.dto;

import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private String msg;
    private int statusCode;
    private T data;

    public ResponseDto(String msg, int statusCode, T data){
        this.msg = msg;
        this.statusCode = statusCode;
        this.data = data;
    }
}
