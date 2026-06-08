package br.com.globalsolution.agrosat.core.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalsolution.agrosat.core.domainmodel.User;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.TokenConfig;
import br.com.globalsolution.agrosat.core.presentation.dto.request.Auth.LoginRequest;
import br.com.globalsolution.agrosat.core.presentation.dto.response.Auth.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoint responsável pela autenticação de usuários e geração do token JWT.")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenConfig tokenConfig;

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica um usuário com email e senha e retorna um token JWT para acesso aos endpoints protegidos.")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest body) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.email(),
                        body.password()));

        User user = (User) authentication.getPrincipal();

        String token = tokenConfig.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
