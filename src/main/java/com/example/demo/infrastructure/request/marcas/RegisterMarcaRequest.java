package com.example.demo.infrastructure.request.marcas;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder @JsonInclude(JsonInclude.Include.NON_NULL)
public record RegisterMarcaRequest(
        @NotBlank(message = "{marca.nome.not.blank}") String marca
) {
}