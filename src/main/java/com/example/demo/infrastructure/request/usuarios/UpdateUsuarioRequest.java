package com.example.demo.infrastructure.request.usuarios;

import com.example.demo.application.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUsuarioRequest extends UsuarioDTO {
}
