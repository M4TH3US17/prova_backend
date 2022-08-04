package com.example.demo.entities.dto;

import com.example.demo.entities.enums.*;
import lombok.*;

import javax.validation.constraints.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CarroDTO {

    @NotBlank(message = "{carro.nome.not.blank}")
    private String  nome;

    @NotNull(message  = "{carro.preco.not.null}")
    private Double  preco;

    @NotNull(message  = "{carro.km.not.null}")
    private Double  km;

    @NotNull(message  = "{carro.reservado.not.null}")
    private Boolean reservado;

    @NotBlank(message = "{carro.url.imagem.not.blank}")
    private String  urlImagem;

    @NotNull(message  = "{carro.ano.not.null}")
    private Integer ano;

    private Cor     cor;
    private Tipo    tipo;

    @NotNull(message  = "{carro.marcaId.not.null}")
    private Long    marcaId;
}
