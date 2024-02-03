package com.example.demo.infrastructure.request.usuarios;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegisterUsuarioRequest(
        @NotBlank(message = "{usuario.login.not.blank}") String username,
        String senha
) {}
