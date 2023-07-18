package com.example.demo.infrastructure.request.marcas;

import com.example.demo.application.dto.MarcaDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterMarcaRequest extends MarcaDTO {
}
