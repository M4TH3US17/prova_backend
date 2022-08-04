package com.example.demo.exceptions;

import com.example.demo.exceptions.notfound.*;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @ExceptionHandler({Exception.class, io.jsonwebtoken.SignatureException.class})
    protected ResponseEntity<Object> apiErro(Exception ex) {

        ErrorDetails erro = new ErrorDetails(LocalDateTime.now().format(formatter),
                HttpStatus.NOT_ACCEPTABLE.value(), ex.getLocalizedMessage());

        return new ResponseEntity<>(erro, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

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

    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ErrorDetails> violacaoForeignKey(Exception e) {
        ErrorDetails erro = new ErrorDetails();
        erro.setStatus(HttpStatus.CONFLICT.value());
        erro.setTimestamp(LocalDateTime.now().format(formatter));
        erro.setError("Violação de Foreign Key.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> erroDeDescerelizacao(Exception ex){
        String msg = "Marca ou Cor estão escritas da forma errada.";
        ErrorDetails erro = new ErrorDetails(LocalDateTime.now().format(formatter), HttpStatus.BAD_REQUEST.value(), msg);
        return new ResponseEntity<>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}