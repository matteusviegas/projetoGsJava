package br.com.globalsolution.agrosat.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalsolution.agrosat.Service.State.StateService;
import br.com.globalsolution.agrosat.presentation.dto.response.State.StateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/states")
@Tag(name = "Estados", description = "Endpoints responsáveis pela consulta dos estados da plataforma AgroSat.")
public class StateController {

    private final StateService stateService;

    @GetMapping
    @Operation(summary = "Listar estados", description = "Retorna todos os estados cadastrados na plataforma.")
    public ResponseEntity<List<StateResponse>> getAll() {
        return ResponseEntity.ok(
                stateService.findAll()
                        .stream()
                        .map(StateResponse::from)
                        .toList());
    }

}
