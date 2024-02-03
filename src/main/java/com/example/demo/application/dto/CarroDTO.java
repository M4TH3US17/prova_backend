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
@Builder @JsonInclude(JsonInclude.Include.NON_NULL)
public record CarroDTO(
        @NotBlank(message = "{carro.nome.not.blank}") String nome,
        @NotNull(message = "{carro.preco.not.null}") Double preco,
        @NotNull(message = "{carro.km.not.null}") Double km,
        @NotNull(message = "{carro.reservado.not.null}") Boolean reservado,
        @NotBlank(message = "{carro.url.imagem.not.blank}") String urlImagem,
        @NotNull(message = "{carro.ano.not.null}") Integer ano,
        @NotBlank(message = "{carro.modelo.not.blank}") String modelo,
        Cor cor,
        Tipo tipo,
        @NotNull(message = "{carro.marcaId.not.null}") MarcaDTO marca,
        Boolean disabled
) { }