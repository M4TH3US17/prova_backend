package com.example.demo.infrastructure.request.usuarios;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) @Data
public class AutenticateRequest {

    private String username;
    private String senha;
}
