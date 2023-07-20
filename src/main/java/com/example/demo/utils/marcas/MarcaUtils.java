package com.example.demo.utils.marcas;

import com.example.demo.application.dto.MarcaDTO;
import com.example.demo.domain.entities.*;
import com.example.demo.infrastructure.request.marcas.RegisterMarcaRequest;

public class MarcaUtils {

    /**
     * Monta um objeto Marca a partir de uma entidade RegisterMarcaRequest
     * @return Marca
     * */
    public static Marca makeMarcaCreatedEntity(RegisterMarcaRequest create) {
        return Marca.builder()
                .marca(create.getMarca())
                .build();
    }

    public static MarcaDTO makeMarcaDTOByEntity(Marca request) {
        return MarcaDTO.builder()
                .id(request.getId())
                .marca(request.getMarca())
                .build();
    }

    public static Marca makeMarcaEntityByDTO(MarcaDTO request) {
        return Marca.builder()
                .id(request.getId())
                .marca(request.getMarca())
                .build();
    }
}
