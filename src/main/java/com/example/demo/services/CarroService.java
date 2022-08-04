package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.entities.dto.CarroDTO;
import com.example.demo.exceptions.notfound.CarroNotFoundException;
import com.example.demo.exceptions.notfound.MarcaNotFoundException;
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

    public Page<Carro> findCarsByYear(Integer ano, Pageable pageable) {
        return repository.findCarroByAno(ano, pageable);
    }

    public Page<Carro> findCarsByBrand(String marca, Pageable pageable) {
        return repository.findCarroByMarca_marcaIgnoreCase(marca, pageable);
    }

    public Carro findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new CarroNotFoundException("Carro com id " + id + " não foi localizado."));
    }

    public Carro save(CarroDTO carroDTO) throws Exception {
        Carro carro = new Carro();
        updateData(carro, carroDTO);
        return repository.save(carro);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Carro update(Long id, CarroDTO carroDTO) throws Exception {
        Carro carroDoBanco = repository.findById(id)
                .orElseThrow(() -> new CarroNotFoundException("Carro com id " + id + " não foi localizado."));
        updateData(carroDoBanco, carroDTO);
        return repository.save(carroDoBanco);
    }

    private void updateData(Carro carroDoBanco, CarroDTO carroDTO) throws Exception {
        Marca marca = marcaRepository.findById(carroDTO.getMarcaId())
                .orElseThrow(() -> new MarcaNotFoundException("Marca com id " + carroDTO.getMarcaId() + " não foi localizada."));

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
