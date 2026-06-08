package br.com.globalsolution.agrosat.presentation.controllers;

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

import br.com.globalsolution.agrosat.domainmodel.Farm;
import br.com.globalsolution.agrosat.domainmodel.User;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.presentation.dto.request.Farm.CreateFarmRequest;
import br.com.globalsolution.agrosat.presentation.dto.request.Farm.UpdateFarmRequest;
import br.com.globalsolution.agrosat.presentation.dto.response.Farm.FarmResponse;
import br.com.globalsolution.agrosat.service.Farm.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/farms")
@Tag(name = "Fazendas", description = "Endpoints responsáveis pelo gerenciamento das fazendas da plataforma AgroSat.")
public class FarmController {

    private final FarmService farmService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar fazenda por ID", description = "Retorna os dados de uma fazenda específica, identificada pelo seu ID.")
    public ResponseEntity<FarmResponse> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!farmService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta fazenda.");
        }

        return ResponseEntity.ok(
                FarmResponse.from(farmService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Cadastrar fazenda", description = "Cria uma nova fazenda na plataforma.")
    public ResponseEntity<FarmResponse> postNew(
            @Valid @RequestBody CreateFarmRequest body,
            @AuthenticationPrincipal JwtUserData authUser) {

        Farm farm = CreateFarmRequest.to(body);
        farm.setUser(new User(authUser.userId()));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(FarmResponse.from(farmService.create(farm)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar fazenda", description = "Atualiza os dados de uma fazenda específica, identificada pelo seu ID.")
    public ResponseEntity<FarmResponse> putById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFarmRequest body,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!farmService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta fazenda.");
        }

        return ResponseEntity.ok(
                FarmResponse.from(
                        farmService.updateById(
                                id,
                                UpdateFarmRequest.to(body))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover fazenda", description = "Remove uma fazenda específica, identificada pelo seu ID.")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!farmService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta fazenda.");
        }

        farmService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
