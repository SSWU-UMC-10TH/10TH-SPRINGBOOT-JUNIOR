//package com.example.umc10th.global.apiPayload.code;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.client.HttpClientErrorException;
//
//@Getter
//@RequiredArgsConstructor
//public enum GeneralErrorCode implements BaseErrorCode{
//
//    BAD_REQUEST(HttpStatus.BAD_REQUEST,
//            code: "COMMON400_1",
//            message: "잘못된 요청입니다."),
//
//    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
//            code: "COMMON401_1",
//            message: "인증되지 않았습니다."),
//
//    FORBIDDEN(HttpStatus.FORBIDDEN,
//            code: "COMMON403_1",
//            message: "접근이 금지되었습니다."),
//
//    NOT_FOUND(HttpStatus.NOT_FOUND,
//            code: "COMMON404_1",
//            message: "해당 리소스를 찾을 수 없습니다."),
//
//    private final HttpStatus status;
//    private final String code;
//    private final String message;
//}
