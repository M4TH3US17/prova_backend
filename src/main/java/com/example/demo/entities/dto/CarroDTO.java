package com.example.demo.entities.dto;

import com.example.demo.entities.enums.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CarroDTO {

    private String  nome;
    private Double  preco;
    private Double  km;
    private Boolean reservado;
    private String  urlImagem;
    private Integer ano;
    private Cor     cor;
    private Tipo    tipo;
    private Long    marcaId;
}
