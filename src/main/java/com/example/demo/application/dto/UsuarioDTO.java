package com.example.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder  @JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioDTO(
        @NotBlank(message = "{usuario.login.not.blank}") String username
) implements Serializable {
}