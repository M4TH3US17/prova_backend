package com.example.demo.application.dto;

import com.example.demo.domain.entities.enums.Cor;
import com.example.demo.domain.entities.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder @JsonInclude(JsonInclude.Include.NON_NULL)
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

    @NotBlank(message = "{carro.modelo.not.blank}")
    private String  modelo;
    private Cor cor;
    private Tipo tipo;

    @NotNull(message  = "{carro.marcaId.not.null}")
    private MarcaDTO    marca;

    private Boolean disabled;
}
