package com.example.onecar.controller.advice;

import com.example.onecar.dto.response.OneCarHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public OneCarHttpResponse<Void> handleValidations(MethodArgumentNotValidException ex) {
        return OneCarHttpResponse.<Void>builder()
                .status(OneCarHttpResponse.Status.BAD_REQUEST)
                .message(detailMessageValidTarget(ex.getDetailMessageArguments()))
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public OneCarHttpResponse<Void> handleRuntimeEx(RuntimeException ex) {
        return OneCarHttpResponse.<Void>builder()
                .status(OneCarHttpResponse.Status.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
    }

    private String detailMessageValidTarget(Object[] args) {
        if(args == null) return "";
        StringBuilder sb = new StringBuilder();
        for(Object a : args)
            sb.append(a.toString()).append("\n");

        return sb.toString();
    }
}
