package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.entities.dto.CarroDTO;
import com.example.demo.repositories.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CarroService {

    private final CarroRepository repository;
    private final MarcaRepository marcaRepository;

    public Page<Carro> findAllCars(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Carro findById(Long id) {
        return repository.findById(id).get();
    }

    public Carro save(CarroDTO carroDTO) {
        Carro carro = new Carro();
        updateData(carro, carroDTO);
        return repository.save(carro);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Carro update(Long id, CarroDTO carroDTO) {
        Carro carroDoBanco = repository.findById(id).get();
        updateData(carroDoBanco, carroDTO);
        return repository.save(carroDoBanco);
    }

    private void updateData(Carro carroDoBanco, CarroDTO carroDTO) {
        Marca marca = marcaRepository.findById(carroDTO.getMarcaId()).get();

        carroDoBanco          .setNome(carroDTO.getNome());
        carroDoBanco        .setPreco(carroDTO.getPreco());
        carroDoBanco              .setKm(carroDTO.getKm());
        carroDoBanco            .setAno(carroDTO.getAno());
        carroDoBanco.setReservado(carroDTO.getReservado());
        carroDoBanco.setUrlImagem(carroDTO.getUrlImagem());
        carroDoBanco                      .setMarca(marca);
        carroDoBanco            .setCor(carroDTO.getCor());
        carroDoBanco          .setTipo(carroDTO.getTipo());
    }
}
