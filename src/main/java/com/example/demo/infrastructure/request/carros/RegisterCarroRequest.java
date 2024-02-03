package com.example.demo.infrastructure.request.carros;

import com.example.demo.application.dto.MarcaDTO;
import com.example.demo.domain.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.validation.constraints.*;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegisterCarroRequest(
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