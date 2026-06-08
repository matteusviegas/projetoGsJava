package br.com.globalsolution.agrosat.core.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.core.presentation.dto.request.Plantation.CreatePlantationRequest;
import br.com.globalsolution.agrosat.core.presentation.dto.request.Plantation.UpdatePlantationRequest;
import br.com.globalsolution.agrosat.core.presentation.dto.response.Plantation.PlantationResponse;
import br.com.globalsolution.agrosat.core.service.Farm.FarmService;
import br.com.globalsolution.agrosat.core.service.Plantation.PlantationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/plantations")
@Tag(name = "Plantações", description = "Endpoints responsáveis pelo gerenciamento das plantações da plataforma AgroSat.")
public class PlantationController {

    private final PlantationService plantationService;

    private final FarmService farmService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar plantação por ID", description = "Retorna os dados de uma plantação específica, identificada pelo seu ID.")
    public ResponseEntity<PlantationResponse> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!plantationService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta plantação.");
        }

        return ResponseEntity.ok(
                PlantationResponse.from(plantationService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Cadastrar plantação", description = "Cria uma nova plantação na plataforma.")
    public ResponseEntity<PlantationResponse> postNew(
            @Valid @RequestBody CreatePlantationRequest body,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!farmService.isOwner(body.getFarmId(), authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta fazenda.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PlantationResponse.from(
                        plantationService.create(
                                CreatePlantationRequest.to(body))));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar plantação", description = "Atualiza os dados de uma plantação específica, identificada pelo seu ID.")
    public ResponseEntity<PlantationResponse> putById(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePlantationRequest body,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!plantationService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta plantação.");
        }

        return ResponseEntity.ok(
                PlantationResponse.from(
                        plantationService.updateById(
                                id,
                                UpdatePlantationRequest.to(body))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover plantação", description = "Remove uma plantação específica, identificada pelo seu ID.")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!plantationService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta plantação.");
        }

        plantationService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
