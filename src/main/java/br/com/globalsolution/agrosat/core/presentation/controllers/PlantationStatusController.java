package br.com.globalsolution.agrosat.core.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalsolution.agrosat.core.presentation.dto.response.PlantationStatus.PlantationStatusResponse;
import br.com.globalsolution.agrosat.core.service.PlantationStatus.PlantationStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/plantation-status")
@Tag(name = "Status de Plantações", description = "Endpoints responsáveis pela consulta dos status de plantações da plataforma AgroSat.")
public class PlantationStatusController {

    private final PlantationStatusService plantationStatusService;

    @GetMapping
    @Operation(summary = "Listar status de plantações", description = "Retorna todos os status de plantações cadastrados na plataforma.")
    public ResponseEntity<List<PlantationStatusResponse>> getAll() {
        return ResponseEntity.ok(
                plantationStatusService.findAll()
                        .stream()
                        .map(PlantationStatusResponse::from)
                        .toList());
    }

}
