package com.alura.foro.exception;

import com.alura.foro.util.ConstantService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.logging.Logger;

@RestControllerAdvice
public class TracerExceptions {

    Logger logger = Logger.getLogger(TracerExceptions.class.getName());
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.warning("MethodArgumentTypeMismatchException: "+e.getMessage());
        return ResponseEntity.status(406).body(ConstantService.ERROR_NOT_ACCEPTABLE+"\n"+ e.getMessage()+"\n");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder();
        errors.forEach(error -> message.append(error.getDefaultMessage()).append("\n"));
        logger.warning("MethodArgumentNotValidException: "+message);
        return ResponseEntity.status(412).body(ConstantService.VALIDATION_FAILED+"\n"+ message);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.warning("HttpRequestMethodNotSupportedException: "+e.getMessage());
        return ResponseEntity.status(405).body(ConstantService.ERROR_METHOD_NOT_ALLOWED+"\n"+ e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.warning("DataIntegrityViolationException: "+e.getMessage());
        return ResponseEntity.status(409).body(ConstantService.ERROR_CONFLICT+"\n"+ e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.warning("HttpMessageNotReadableException: "+e.getMessage());
        return ResponseEntity.status(400).body(ConstantService.ERROR_BAD_REQUEST+"\n"+ e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException e) {
        logger.warning("IllegalArgumentException: "+e.getMessage());
        return ResponseEntity.status(412).body(ConstantService.VALIDATION_FAILED+"\n"+ e.getMessage());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> unexpectedTypeException(UnexpectedTypeException e) {
        logger.warning("UnexpectedTypeException: "+e.getMessage());
        return ResponseEntity.status(400).body(ConstantService.ERROR_BAD_REQUEST+"\n"+ e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException e) {
        logger.warning("ConstraintViolationException: "+e.getMessage());
        return ResponseEntity.status(412).body(ConstantService.VALIDATION_FAILED+"\n"+ e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsException(BadCredentialsException e) {
        logger.warning("BadCredentialsException: "+e.getMessage());
        return ResponseEntity.status(401).body(ConstantService.ERROR_UNAUTHORIZED+"\n"+ e.getMessage());
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> jsonParseException(JsonParseException e) {
        logger.warning("JsonParseException: "+e.getMessage());
        return ResponseEntity.status(400).body(ConstantService.ERROR_BAD_REQUEST+"\n"+ e.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<Object> badRequestException(HttpClientErrorException.BadRequest e) {
        logger.warning("HttpClientErrorException.BadRequest: "+e.getMessage());
        return ResponseEntity.status(400).body(ConstantService.ERROR_BAD_REQUEST+"\n"+ e.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> forbiddenException(HttpClientErrorException.Forbidden e) {
        logger.warning("HttpClientErrorException.Forbidden: "+e.getMessage());
        return ResponseEntity.status(403).body(ConstantService.ERROR_FORBIDDEN+"\n"+ e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> authenticationException(AuthenticationException e) {
        logger.warning("AuthenticationException: "+e.getMessage());
        return ResponseEntity.status(401).body(ConstantService.ERROR_UNAUTHORIZED+"\n"+ e.getMessage());
    }

}