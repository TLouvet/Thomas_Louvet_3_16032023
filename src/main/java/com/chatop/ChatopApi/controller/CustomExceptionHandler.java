package com.chatop.ChatopApi.controller;

import com.chatop.ChatopApi.dto.response.BadRequestResponse;
import com.chatop.ChatopApi.dto.response.SingleMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestResponse> handleValidationExceptions(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(new BadRequestResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLConstraintException(SQLIntegrityConstraintViolationException exception){
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<SingleMessageResponse> handleBadCredentialsException(BadCredentialsException exception){
        SingleMessageResponse response = new SingleMessageResponse();
        response.setMessage("error");
        return new ResponseEntity<>( response,HttpStatus.UNAUTHORIZED);
    }
}
