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

        if(listaDeMarcas.isEmpty()) {
            return MarcaResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Nenhuma marca encontrada.")
                    .build();
        } else {
            return MarcaResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Segue a lista de marcas!")
                    .data(listaDeMarcas)
                    .build();
        }
    }

    @Transactional
    public MarcaResponse save(RegisterMarcaRequest request) {
        try {
            log.info("MarcaService :: Iniciando etapa de persistencia...");
            Marca marca = MarcaUtils.makeMarcaCreatedEntity(request);
            log.info("MarcaService :: Cadastrando no banco...");
            MarcaDTO marcaDTO = MarcaUtils.makeCarroDTOByEntity(repository.save(marca));
            log.info("MarcaService :: Marca {} salva no banco de dados!", request.getMarca());

            return MarcaResponse.builder()
                    .data(HttpStatus.OK.value())
                    .message("Marca cadastrada com sucesso!")
                    .data(marcaDTO)
                    .build();
        } catch(Exception error) {
            log.error("ERROR: " + error);
            return MarcaResponse.builder()
                    .data(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve um erro ao salvar marca!")
                    .build();
        }
    }
}