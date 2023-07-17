package com.example.demo.application.exceptions.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarroNotFoundException extends Exception {

    public CarroNotFoundException(String msg) {
        super(msg);
    }
}
