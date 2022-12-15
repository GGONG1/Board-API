package com.team9.boardapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),

    //404 NOT_FOUND 잘못된 리소스 접근
    CONTENT_NOT_FOUND(404, "존재하지 않는 게시글 입니다."),
    AUTHORIZATION_DELETE_FAIL(404, "삭제 권한이 없습니다."),
    AUTHORIZATION_UPDATE_FAIL(404, "수정 권한이 없습니다."),

    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!");

    private final int status;
    private final String message;
}
