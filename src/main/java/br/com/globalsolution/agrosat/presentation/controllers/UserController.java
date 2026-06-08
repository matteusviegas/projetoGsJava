package br.com.globalsolution.agrosat.presentation.controllers;

import java.util.List;

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

import br.com.globalsolution.agrosat.domainmodel.User;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.presentation.dto.request.User.CreateUserRequest;
import br.com.globalsolution.agrosat.presentation.dto.request.User.UpdateUserRequest;
import br.com.globalsolution.agrosat.presentation.dto.response.Farm.FarmResponse;
import br.com.globalsolution.agrosat.presentation.dto.response.User.UserResponse;
import br.com.globalsolution.agrosat.service.Farm.FarmService;
import br.com.globalsolution.agrosat.service.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Tag(name = "Usuários", description = "Operações para gerenciamento de usuários, incluindo cadastro, consulta, atualização e remoção.")
public class UserController {

    private final UserService userService;

    private final FarmService farmService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico, identificado pelo seu ID.")
    public ResponseEntity<UserResponse> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!authUser.userId().equals(id)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar este usuário.");
        }

        return ResponseEntity.ok(
                UserResponse.from(userService.findById(id)));
    }

    @GetMapping("/{id}/farms")
    @Operation(summary = "Listar fazendas do usuário", description = "Retorna todas as fazendas associadas a um usuário específico, identificado pelo seu ID.")
    public ResponseEntity<List<FarmResponse>> getFarmsByUserId(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!authUser.userId().equals(id)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar estas fazendas.");
        }

        User user = userService.findById(id);
        return ResponseEntity.ok(farmService.findAllByUser(user)
                .stream()
                .map(FarmResponse::from)
                .toList());
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário no sistema.")
    public ResponseEntity<UserResponse> postNew(
            @Valid @RequestBody CreateUserRequest body) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.from(userService.create(
                        CreateUserRequest.to(body))));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente identificado pelo seu ID.")
    public ResponseEntity<UserResponse> putById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest body,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!authUser.userId().equals(id)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar este usuário.");
        }

        return ResponseEntity.ok(
                UserResponse.from(
                        userService.updateById(
                                id,
                                body.actualPassword(),
                                UpdateUserRequest.to(body))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover usuário", description = "Remove um usuário do sistema identificado pelo seu ID.")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!authUser.userId().equals(id)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar este usuário.");
        }

        userService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
