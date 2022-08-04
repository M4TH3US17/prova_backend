package com.example.demo.exceptions;

import com.example.demo.exceptions.notfound.*;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @ExceptionHandler({CarroNotFoundException.class, MarcaNotFoundException.class})
    public ResponseEntity<ErrorDetails> entidadeNotFoundException(Exception e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now().format(formatter), HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    protected ResponseEntity<Map<String, ErrorDetails>> validacaoCamposException(MethodArgumentNotValidException ex) {
        Map<String, ErrorDetails> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldError = ((FieldError) error).getField();
            ErrorDetails err = new ErrorDetails();
            err.setError(error.getDefaultMessage());
            err.setTimestamp(LocalDateTime.now().format(formatter));
            err.setStatus(HttpStatus.BAD_REQUEST.value());
            map.put(fieldError, err);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
}