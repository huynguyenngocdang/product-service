package com.huynguyenngocdang.product_service.exception;

import com.huynguyenngocdang.product_service.common.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.huynguyenngocdang.product_service.constants.ApiResponse.ERROR_CODE;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi<Void>> handleGlobalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseApi.error(ERROR_CODE, e.getMessage()));
    }
}
