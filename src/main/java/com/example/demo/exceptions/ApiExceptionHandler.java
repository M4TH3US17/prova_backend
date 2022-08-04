package com.example.demo.exceptions;

import com.example.demo.exceptions.notfound.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ApiExceptionHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @ExceptionHandler({CarroNotFoundException.class, MarcaNotFoundException.class})
    public ResponseEntity<ErrorDetails> entidadeNotFoundException(Exception e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now().format(formatter), HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}