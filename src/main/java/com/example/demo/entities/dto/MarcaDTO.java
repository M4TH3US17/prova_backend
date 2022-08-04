package com.example.demo.entities.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class MarcaDTO {

    @NotBlank(message = "{marca.nome.not.blank}")
    private String marca;

}
