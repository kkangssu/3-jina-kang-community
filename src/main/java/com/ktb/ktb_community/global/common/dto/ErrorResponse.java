package com.ktb.ktb_community.global.common.dto;

import com.ktb.ktb_community.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private boolean success;
    private ErrorDetail error;
    private LocalDateTime timestamp;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .success(false)
                .error(ErrorDetail.builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .status(errorCode.getStatus().value())
                        .build())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .success(false)
                .error(ErrorDetail.builder()
                    .code(errorCode.name())
                    .message(message)
                    .status(errorCode.getStatus().value())
                    .build())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ErrorDetail {
        private String code;
        private String message;
        private int status;
    }
}
