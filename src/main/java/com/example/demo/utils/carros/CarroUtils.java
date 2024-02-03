package com.example.demo.utils.carros;

import com.example.demo.application.dto.CarroDTO;
import com.example.demo.domain.entities.Carro;
import com.example.demo.infrastructure.request.carros.*;
import com.example.demo.utils.marcas.MarcaUtils;

public class CarroUtils {


    /**
     * Constrói e retorna uma entidade Carro atualizada com base nos dados fornecidos por uma requisição de atualização (UpdateCarroRequest).
     *
     * @param request uma instância de UpdateCarroRequest contendo os dados de atualização do Carro.
     * @param carro uma instância existente de Carro a ser atualizada.
     * @return uma entidade Carro atualizada.
     */
    public static Carro makeCarroUpdatedEntity(UpdateCarroRequest request, Carro carro) {
        Carro carroAtualizado = Carro.builder()
                .id(carro.getId())
                .nome(request.nome())
                .km(request.km())
                .ano(request.ano())
                .cor(request.cor())
                .preco(request.preco())
                .tipo(request.tipo())
                .modelo(request.modelo())
                .reservado(request.reservado())
                .urlImagem(request.urlImagem())
                .marca(MarcaUtils.makeMarcaEntityByDTO(request.marca()))
                .disabled(false)
                .build();

        return carroAtualizado;
    }

    /**
     * Constrói e retorna um objeto CarroDTO com base nos dados fornecidos por uma entidade Carro.
     *
     * @param carro uma instância de Carro contendo os dados para criação do CarroDTO.
     * @return um objeto CarroDTO.
     */
    public static CarroDTO makeCarroDTOByEntity(Carro carro) {
        return CarroDTO.builder()
                .nome(carro.getNome())
                .preco(carro.getPreco())
                .ano(carro.getAno())
                .tipo(carro.getTipo())
                .cor(carro.getCor())
                .modelo(carro.getModelo())
                .reservado(carro.getReservado())
                .urlImagem(carro.getUrlImagem())
                .km(carro.getKm())
                .marca(MarcaUtils.makeMarcaDTOByEntity(carro.getMarca()))
                .disabled(carro.getDisabled())
                .build();
    }

    /**
     * Constrói e retorna uma entidade Carro com base nos dados fornecidos por uma requisição de criação (RegisterCarroRequest).
     *
     * @param request uma instância de RegisterCarroRequest contendo os dados para criação do Carro.
     * @return uma entidade Carro recém-criada.
     */
    public static Carro makeCarroCreatedEntity(RegisterCarroRequest request) {
        return Carro.builder()
                .nome(request.nome())
                .preco(request.preco())
                .ano(request.ano())
                .tipo(request.tipo())
                .cor(request.cor())
                .modelo(request.modelo())
                .reservado(request.reservado())
                .urlImagem(request.urlImagem())
                .km(request.km())
                .disabled(false)
                .build();
    }

}
