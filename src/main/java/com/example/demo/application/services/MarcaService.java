package com.example.demo.application.services;

import com.example.demo.application.dto.MarcaDTO;
import com.example.demo.domain.entities.Marca;
import com.example.demo.domain.repositories.MarcaRepository;
import com.example.demo.infrastructure.request.marcas.RegisterMarcaRequest;
import com.example.demo.infrastructure.response.marcas.MarcaResponse;
import com.example.demo.utils.marcas.MarcaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class MarcaService {

    private final MarcaRepository repository;

    public MarcaResponse findAll() {
        List<Marca> listaDeMarcas = repository.findAll();

        if(listaDeMarcas.isEmpty())
            return returnsError404NotFoundResponse("Nenhuma marca encontrada.", null);

            return MarcaResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Segue a lista de marcas!")
                    .data(listaDeMarcas)
                    .build();
    }

    @Transactional
    public MarcaResponse save(RegisterMarcaRequest request) {
        try {
            log.info("MarcaService :: Iniciando etapa de persistencia...");
            Marca marca = MarcaUtils.makeMarcaCreatedEntity(request);
            log.info("MarcaService :: Cadastrando no banco...");
            MarcaDTO marcaDTO = MarcaUtils.makeMarcaDTOByEntity(repository.save(marca));
            log.info("MarcaService :: Marca {} salva no banco de dados!", request.marca());

            return MarcaResponse.builder()
                    .data(HttpStatus.OK.value())
                    .message("Marca cadastrada com sucesso!")
                    .data(marcaDTO)
                    .build();
        } catch(Exception error) {
            return returnsError500InternalServerErrorResponse(error);
        }
    }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private <T> T returnsError404NotFoundResponse(String message, Object responseBody) {
        log.info("MarcaService :: Não foi possivel encontrar nenhuma marca!");
        int code = HttpStatus.NOT_FOUND.value();

        return (T) new MarcaResponse(code, message, responseBody);
    }

    private <T> T returnsError500InternalServerErrorResponse(Exception error) {
        log.error(error.getLocalizedMessage());
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String messageError = "Ocorreu um erro desconhecido!";

        return (T) new MarcaResponse(code, messageError, error);
    }
}