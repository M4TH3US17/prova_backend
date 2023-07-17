package com.example.demo.application.services;

import com.example.demo.application.dto.CarroDTO;
import com.example.demo.application.exceptions.notfound.CarroNotFoundException;
import com.example.demo.application.exceptions.notfound.MarcaNotFoundException;
import com.example.demo.domain.entities.Carro;
import com.example.demo.domain.entities.Marca;
import com.example.demo.domain.repositories.CarroRepository;
import com.example.demo.domain.repositories.MarcaRepository;
import com.example.demo.infrastructure.request.carros.RegisterCarroRequest;
import com.example.demo.infrastructure.request.carros.UpdateCarroRequest;
import com.example.demo.infrastructure.response.carros.CarroResponse;
import com.example.demo.utils.carros.CarroUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Slf4j
public class CarroService {

    private final CarroRepository repository;
    private final MarcaRepository marcaRepository;

    public CarroResponse findAllCars(Pageable pageable) {
        log.info("CarroService :: Obtendo todos os carros cadastrados no sistema...");
        List<Carro> listaDeCarros =  repository.findAll();

        if (listaDeCarros.isEmpty()){
            log.info("CarroService :: Nao há carros cadastrados!");
            return CarroResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Nenhum carro localizado!")
                    .build();
        } else {
            log.info("CarroService :: Lista de carros montada!");
            return CarroResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Segue a lista de todos os carros encontrados!")
                    .data(listaDeCarros)
                    .build();
        }
    }

    public CarroResponse findCarsByYear(Integer ano) {
        try {
            log.info("CarroService :: Obtendo todos os carros cadastrados no ano { } ...", ano);
            List<Carro> listaDeCarros = repository.findCarroByAno(ano);

            if (listaDeCarros.isEmpty()) {
                log.info("CarroService :: Nao foi encontrado nenhum carro cadastrado no ano { }.", ano);
                return CarroResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("Carro nao localizado!")
                        .build();
            } else {
                log.info("CarroService :: Montando lista...");
                return CarroResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Segue a lista de carros encontrados!")
                        .data(listaDeCarros)
                        .build();
            }
        } catch (Exception error) {
            log.error("ERROR: " + error);
            return CarroResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve um erro no servidor!")
                    .build();
        }
    }

    public CarroResponse findCarsByBrand(String marca) {
        try {
            log.info("CarroService :: Obtendo todos os carros da marca no ano { } ...", marca);
            List<Carro> listaDeCarros = repository.findCarroByMarca_marcaIgnoreCase(marca);

            if (listaDeCarros.isEmpty()) {
                log.info("CarroService :: Nao foi encontrado nenhum carro cuja marca seja { }.", marca);
                return CarroResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("Carro nao localizado!")
                        .build();
            } else {
                log.info("CarroService :: Montando lista...");
                return CarroResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Segue a lista de carros encontrados!")
                        .data(listaDeCarros)
                        .build();
            }
        } catch (Exception error) {
            log.error("ERROR: " + error);
            return CarroResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve um erro no servidor!")
                    .build();
        }
    }

    public CarroResponse findCarroByModelo(String modelo) {
        try {
            log.info("CarroService :: Obtendo todos os carros do modelo { } ...", modelo);
            List<Carro> listaDeCarros = repository.findCarroByModeloIgnoreCase(modelo);

            if (listaDeCarros.isEmpty()) {
                log.info("CarroService :: Nao foi encontrado nenhum carro cujo modelo seja { }.", modelo);
                return CarroResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("Carro nao localizado!")
                        .build();
            } else {
                log.info("CarroService :: Montando lista...");
                return CarroResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Segue a lista de carros encontrados!")
                        .data(listaDeCarros)
                        .build();
            }
        } catch (Exception error) {
            log.error("ERROR: " + error);
            return CarroResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve um erro no servidor!")
                    .build();
        }
    }

    public CarroResponse findById(Long id) throws Exception {
        try {
            log.info("CarroService :: Obtendo carro pelo id passado...");
            Optional<Carro> carro = repository.findById(id);

            if (carro.isPresent()) {
                log.info("CarroService :: Carro encontrado na base de dados!");
                return CarroResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Segue os dados do carro encontrado").data(carro)
                        .build();
            } else {
                log.info("CarroService :: Carro nao encontrado!");
                return CarroResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("Carro nao localizado!")
                        .build();
            }
        } catch (Exception error) {
            log.error("ERROR: " + error);
            return CarroResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve um erro no servidor!")
                    .build();
        }
    }

    @Transactional
    public CarroResponse save(RegisterCarroRequest request) throws Exception {
        try {
            log.info("CarroService :: Iniciando etapa de persistencia de nova carro...");
            Carro carro = CarroUtils.makeCarroCreatedEntity(request);
            log.info("CarroService :: Salvando carro...");
            CarroDTO carroDTO = CarroUtils.makeCarroDTOByEntity(repository.save(carro));
            log.info("CarroService :: Carro cadastrado com sucesso!");

            return CarroResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .message("Carro salvo com sucesso!")
                    .data(carroDTO)
                    .build();
        } catch(Exception error) {
            log.error("ERROR: " + error.getLocalizedMessage());
            return CarroResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve erro durante o processo!")
                    .build();
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public CarroResponse update(Long id, UpdateCarroRequest request) throws Exception {
        try {
            Carro carroDoBanco = repository.findById(id).orElseThrow(() -> new CarroNotFoundException("Carro com id " + id + " não foi localizado."));
            log.info("CarroService :: Carro encontrado na base de dados...");
            Carro carro = CarroUtils.makeCarroUpdatedEntity(request, carroDoBanco);
            log.info("CarroService :: Salvando carro atualizado...");
            CarroDTO carroDTO = CarroUtils.makeCarroDTOByEntity(repository.save(carro));
            log.info("CarroService :: Carro atualizado com sucesso!");

            return CarroResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Carro foi atualizado com sucesso!")
                    .data(carroDTO)
                    .build();
    } catch(Exception error) {
        log.error("ERROR: " + error.getLocalizedMessage());
        return CarroResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Houve erro durante o processo atualizacao!")
                .build();
    }
    }

}
