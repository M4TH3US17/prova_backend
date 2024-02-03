package com.example.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MarcaDTO(
        Long id,
        @NotBlank(message = "{marca.nome.not.blank}") String marca
) { }
