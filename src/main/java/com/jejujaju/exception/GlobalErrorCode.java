package com.jejujaju.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum GlobalErrorCode implements ErrorCode {
    JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    JWT_NOT_VERIFIED(HttpStatus.FORBIDDEN,"토큰의 시그내처가 유효하지 않습니다."),
    JWT_MALFORMED(HttpStatus.FORBIDDEN,"토큰의 형식이 잘못되었습니다. 토큰은 [header].[payload].[secret]의 형식이어야 합니다."),
    JWT_UNSUPPORTED(HttpStatus.FORBIDDEN,"지원하지 않는 종류의 토큰입니다."),
    LOGIN_DATA_NOT_FOUND(HttpStatus.NOT_FOUND,"로그인 정보가 잘못 되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}
