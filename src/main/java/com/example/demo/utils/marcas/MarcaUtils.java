package com.example.demo.utils.marcas;

import com.example.demo.application.dto.MarcaDTO;
import com.example.demo.domain.entities.*;
import com.example.demo.infrastructure.request.marcas.RegisterMarcaRequest;

public class MarcaUtils {

    /**
     * Cria e retorna uma entidade Marca com base nos dados fornecidos por uma requisição de criação (RegisterMarcaRequest).
     *
     * @param request uma instância de RegisterMarcaRequest contendo os dados para criação da Marca.
     * @return uma entidade Marca recém-criada.
     */
    public static Marca makeMarcaCreatedEntity(RegisterMarcaRequest request) {
        return Marca.builder()
                .marca(request.marca())
                .build();
    }

    /**
     * Constrói e retorna um objeto MarcaDTO com base nos dados fornecidos por uma entidade Marca.
     *
     * @param request uma instância de Marca contendo os dados para criação do MarcaDTO.
     * @return um objeto MarcaDTO.
     */
    public static MarcaDTO makeMarcaDTOByEntity(Marca request) {
        return MarcaDTO.builder()
                .id(request.getId())
                .marca(request.getMarca())
                .build();
    }

    /**
     * Constrói e retorna uma entidade Marca com base nos dados fornecidos por uma requisição de DTO (MarcaDTO).
     *
     * @param request uma instância de MarcaDTO contendo os dados para criação da Marca.
     * @return uma entidade Marca recém-criada.
     */
    public static Marca makeMarcaEntityByDTO(MarcaDTO request) {
        return Marca.builder()
                .id(request.id())
                .marca(request.marca())
                .build();
    }

}
