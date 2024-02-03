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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service @RequiredArgsConstructor @Slf4j
public class CarroService {

    private final CarroRepository repository;
    private final MarcaRepository marcaRepository;

    public CarroResponse findAllCars(Pageable pageable) {
        log.info("CarroService :: Obtendo todos os carros cadastrados no sistema...");
        Page<Carro> listaDeCarros =  repository.findAllByDisabledFalse(pageable);

        if (listaDeCarros.isEmpty()){
            return returnsError404NotFoundResponse("Nenhum carro localizado!", new ArrayList<>());
        } else {
            log.info("CarroService :: Lista de carros montada!");
            return CarroResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Segue a lista de todos os carros encontrados!")
                    .data(listaDeCarros)
                    .build();
        }
    }

    public CarroResponse findCarsByYear(Integer ano, Pageable pageable) {
        try {
            log.info("CarroService :: Obtendo todos os carros cadastrados no ano {} ...", ano);
            Page<Carro> listaDeCarros = repository.findCarroByAnoAndDisabledFalse(ano, pageable);

            if (listaDeCarros.isEmpty()) {
                log.info("CarroService :: Nao foi encontrado nenhum carro cadastrado no ano {}.", ano);
                return returnsError404NotFoundResponse("Nao foi encontrado nenhum carro!", new ArrayList<>());
            } else {
                log.info("CarroService :: Lista montada com sucesso!");
                return CarroResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Segue a lista de carros encontrados!")
                        .data(listaDeCarros)
                        .build();
            }
        } catch (Exception error) {
            log.error("ERROR: " + error);
            return returnsError500InternalServerErrorResponse(error);
        }
    }

    public CarroResponse findCarsByBrand(String marca, Pageable pageable) {
        try {
            log.info("CarroService :: Obtendo todos os carros da marca {} ...", marca);
            Page<Carro> listaDeCarros = repository.findCarroByMarca_marcaIgnoreCaseAndDisabledFalse(marca, pageable);

            if (listaDeCarros.isEmpty()) {
                log.info("CarroService :: Nao foi encontrado nenhum carro cuja marca seja {}.", marca);
                return returnsError404NotFoundResponse("Nao foi encontrado nenhum carro", new ArrayList<>());
            } else {
                log.info("CarroService :: Carros encontrados com sucesso!.");
                return CarroResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Segue a lista de carros encontrados!")
                        .data(listaDeCarros)
                        .build();
            }
        } catch (Exception error) {
            log.error("ERROR: " + error);
            return returnsError500InternalServerErrorResponse(error);
        }
    }

    public CarroResponse findCarroByModelo(String modelo, Pageable pageable) {
        try {
            log.info("CarroService :: Obtendo todos os carros do modelo {} ...", modelo);
            Page<Carro> listaDeCarros = repository.findCarroByModeloIgnoreCaseAndDisabledFalse(modelo, pageable);

            if (listaDeCarros.isEmpty()) {
                log.info("CarroService :: Nao foi encontrado nenhum carro cujo modelo seja {}.", modelo);
                return returnsError404NotFoundResponse("Carros de modelo " + modelo + " nao foram localizados!", new ArrayList<>());

            } else {
                log.info("CarroService :: Carros encontrados com sucesso!");
                return CarroResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Segue a lista de carros encontrados!")
                        .data(listaDeCarros)
                        .build();
            }
        } catch (Exception error) {
            return returnsError500InternalServerErrorResponse(error);
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
                return returnsError404NotFoundResponse("Nao foi encontrado nenhum carro cujo ID seja " + id, null);
            }
        } catch (Exception error) {
            return returnsError500InternalServerErrorResponse(error);
        }
    }

    @Transactional
    public CarroResponse save(RegisterCarroRequest request) throws Exception {
        try {
            log.info("CarroService :: Iniciando etapa de persistencia...");
            Carro carro = CarroUtils.makeCarroCreatedEntity(request);
            log.info("CarroService :: Salvando carro...");
            insereMarcaDoCarro(carro, request.getMarca().getId());
            CarroDTO carroDTO = CarroUtils.makeCarroDTOByEntity(repository.save(carro));
            log.info("CarroService :: Carro cadastrado com sucesso!");

            return CarroResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .message("Carro salvo com sucesso!")
                    .build();
        } catch(Exception error) {
            return returnsError500InternalServerErrorResponse(error);
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

            if (carroDoBanco.isPresent() == false )
               return returnsError404NotFoundResponse("Carro nao localizado na base de dados!!", null);

            log.info("CarroService :: Carro encontrado na base de dados...");
            Carro carro = CarroUtils.makeCarroUpdatedEntity(request, carroDoBanco.get());
            log.info("CarroService :: Salvando carro atualizado...");
            insereMarcaDoCarro(carro, request.getMarca().getId());
            CarroDTO carroDTO = CarroUtils.makeCarroDTOByEntity(repository.save(carro));
            log.info("CarroService :: Carro atualizado com sucesso!");

            return CarroResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Carro foi atualizado com sucesso!")
                    .data(carroDTO)
                    .build();
    } catch(Exception error) {
        return returnsError500InternalServerErrorResponse(error);
    }
    }


    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private <T> T returnsError404NotFoundResponse(String message, Object responseBody) {
        log.info("CartoService :: Não foi possivel encontrar nenhum carro!");
        int code = HttpStatus.NOT_FOUND.value();

        return (T) new CarroResponse(code, message, responseBody);
    }

    private <T> T returnsError500InternalServerErrorResponse(Exception error) {
        log.error(error.getLocalizedMessage());
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String messageError = "Ocorreu um erro desconhecido!";

        return (T) new CarroResponse(code, messageError, error);
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
