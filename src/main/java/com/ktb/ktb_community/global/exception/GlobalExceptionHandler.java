package com.ktb.ktb_community.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ktb.ktb_community.global.common.dto.ErrorResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handlerCustomException(CustomException e) {
        log.error("에러 - code: {}, message: {}", e.getErrorCode(), e.getMessage());

        ErrorResponse response = ErrorResponse.of(e.getErrorCode());

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException e) {
        log.error("Validation 살패 - validation: {}", e.getMessage());

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();     // 필드명
            String errorMessage = error.getDefaultMessage();        // 에러 메시지
            errors.put(fieldName, errorMessage);
        });

        String message = "Validation 실패: " + errors.toString();

        ErrorResponse response = ErrorResponse.of(ErrorCode.VALIDATION_ERROR, message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("서버 에러: ", e);

        ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
