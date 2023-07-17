package com.example.demo.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class ErrorDetails implements Serializable {

    private String  timestamp;
    private Integer status;
    private String  error;
}
