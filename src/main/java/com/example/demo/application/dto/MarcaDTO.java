package com.example.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder @JsonInclude(JsonInclude.Include.NON_NULL)
public class MarcaDTO {

    @NotBlank(message = "{marca.nome.not.blank}")
    private String marca;

}
