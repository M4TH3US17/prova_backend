package com.example.demo.config.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class RespostaDeLogin implements Serializable {

    private String token;
}
