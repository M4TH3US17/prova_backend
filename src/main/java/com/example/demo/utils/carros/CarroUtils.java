package com.example.demo.utils.carros;

import com.example.demo.application.dto.CarroDTO;
import com.example.demo.domain.entities.Carro;
import com.example.demo.domain.entities.Marca;
import com.example.demo.infrastructure.request.carros.RegisterCarroRequest;
import com.example.demo.infrastructure.request.carros.UpdateCarroRequest;
import com.example.demo.infrastructure.response.carros.CarroResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CarroUtils {


    /**
     * Monta uma entidade Exporter para atualização a partir de uma entidade de request UpdateCarroRequest
     * @return Carro
     * */
    public static Carro makeCarroUpdatedEntity(UpdateCarroRequest update, Carro carro) {
        Carro carroDTO = Carro.builder()
                .id(carro.getId())
                .nome(update.getNome())
                .km(update.getKm())
                .cor(update.getCor())
                .marca(new Marca(1L, "a"))
                .preco(update.getPreco())
                .tipo(update.getTipo())
                .modelo(update.getModelo())
                .reservado(update.getReservado())
                .urlImagem(update.getUrlImagem())
                .build();

        return carroDTO;
    }

    /**
     * Monta um objeto CarroDTO a partir de uma entidade Exporter
     * @return CarroDTO
     * */
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
                .marcaId(carro.getMarca().getId())
                .build();
    }

    public static Carro makeCarroCreatedEntity(RegisterCarroRequest carro) {
        return Carro.builder()
                .nome(carro.getNome())
                .preco(carro.getPreco())
                .ano(carro.getAno())
                .tipo(carro.getTipo())
                .cor(carro.getCor())
                .modelo(carro.getModelo())
                .reservado(carro.getReservado())
                .urlImagem(carro.getUrlImagem())
                .km(carro.getKm())
                .marca(null)
                .build();
    }
}
