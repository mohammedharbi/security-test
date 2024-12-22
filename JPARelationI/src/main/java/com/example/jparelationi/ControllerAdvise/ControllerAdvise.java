package com.example.jparelationi.ControllerAdvise;

import com.example.jparelationi.Api.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity ApiException(ApiException e) {
        String msg = e.getMessage();
       return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity DuplicateKeyException(DuplicateKeyException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

}
