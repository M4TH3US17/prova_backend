package com.example.demo.infrastructure.request.carros;

import com.example.demo.application.dto.CarroDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCarroRequest extends CarroDTO {
}
