package com.example.demo.application.services;

import com.example.demo.application.dto.CarroDTO;
import com.example.demo.domain.entities.Carro;
import com.example.demo.domain.entities.Marca;
import com.example.demo.domain.repositories.*;
import com.example.demo.infrastructure.request.carros.*;
import com.example.demo.infrastructure.response.carros.CarroResponse;
import com.example.demo.utils.carros.CarroUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service @RequiredArgsConstructor @Slf4j
public class CarroService {

    private final CarroRepository repository;
    private final MarcaRepository marcaRepository;

    public CarroResponse findAllCars() {
        log.info("CarroService :: Obtendo todos os carros cadastrados no sistema...");
        List<Carro> listaDeCarros =  repository.findAllByDisabledFalseOrderByIdDesc();

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
            List<Carro> listaDeCarros = repository.findCarroByAnoAndDisabledFalse(ano);

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
            List<Carro> listaDeCarros = repository.findCarroByMarca_marcaIgnoreCaseAndDisabledFalse(marca);

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
            List<Carro> listaDeCarros = repository.findCarroByModeloIgnoreCaseAndDisabledFalse(modelo);

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
            Optional<Carro> carro = repository.findByIdAndDisabledFalse(id);

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
            log.info("CarroService :: Iniciando etapa de persistencia...");
            Carro carro = CarroUtils.makeCarroCreatedEntity(request);
            log.info("CarroService :: Salvando carro...");
            insereMarcaDoCarro(carro, request.getMarcaId());
            CarroDTO carroDTO = CarroUtils.makeCarroDTOByEntity(repository.save(carro));
            log.info("CarroService :: Carro cadastrado com sucesso!");

            return CarroResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .message("Carro salvo com sucesso!")
                    .build();
        } catch(Exception error) {
            log.error("ERROR: " + error.getLocalizedMessage());
            return CarroResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve erro durante o processo!")
                    .build();
        }
    }

    public CarroResponse delete(Long id) {
        Carro carro = repository.findById(id).get();
        carro.setDisabled(true);
        repository.save(carro);
        log.info("CarroService :: Carro desativado com sucesso!");
        return CarroResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Carro excluido com sucesso!")
                .build();
    }

    @Transactional
    public CarroResponse update(Long id, UpdateCarroRequest request) throws Exception {
        try {
            Optional<Carro> carroDoBanco = repository.findByIdAndDisabledFalse(id);

            if (carroDoBanco.isPresent() == false ) {
                log.info("CarroService :: Carro nao localizado na base de dados!");
               return CarroResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("Carro nao localizado na base de dados!!")
                        .build();
            }

            log.info("CarroService :: Carro encontrado na base de dados...");
            Carro carro = CarroUtils.makeCarroUpdatedEntity(request, carroDoBanco.get());
            log.info("CarroService :: Salvando carro atualizado...");
            insereMarcaDoCarro(carro, request.getMarcaId());
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

    private void insereMarcaDoCarro(Carro carro, Long idMarca){
        log.info("CarroService :: Inserindo marca de id {} no carro...", idMarca);
        Optional<Marca> marca = marcaRepository.findById(idMarca);
        if(marca.get() != null) {
            log.info("CarroService :: Marca localizada!");
            carro.setMarca(marca.get());
        } else {
            log.error("ERROR: Marca nao foi localizada!");
        }
    }
}
