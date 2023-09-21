package com.alura.foro.exception;

import com.alura.foro.util.ConstantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TracerExceptions {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(406).body(ConstantService.ERROR_NOT_ACCEPTABLE+"\n"+ e.getMessage()+"\n");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(404).body(ConstantService.INFO_NOT_FOUND+"\n"+ e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder();
        errors.forEach(error -> message.append(error.getDefaultMessage()).append("\n"));
        return ResponseEntity.status(400).body(ConstantService.VALIDATION_FAILED+"\n"+ message);
    }
}