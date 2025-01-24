package com.victor.publication.handler;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerMethodException(Exception exception, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        log.error("[ERROR] payload{}",errorResponse, exception);

        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
