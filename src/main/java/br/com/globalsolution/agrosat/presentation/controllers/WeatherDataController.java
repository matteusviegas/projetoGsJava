package br.com.globalsolution.agrosat.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.presentation.dto.request.WeatherData.WeatherDataRequest;
import br.com.globalsolution.agrosat.presentation.dto.response.WeatherData.WeatherDataResponse;
import br.com.globalsolution.agrosat.service.Farm.FarmService;
import br.com.globalsolution.agrosat.service.WeatherData.WeatherDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/weather-datas")
@Tag(name = "Dados Climáticos", description = "Endpoints responsáveis pelo gerenciamento dos dados climáticos da plataforma AgroSat.")
public class WeatherDataController {

    private final WeatherDataService weatherDataService;

    private final FarmService farmService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar dado climático por ID", description = "Retorna os dados de um registro climático específico, identificado pelo seu ID.")
    public ResponseEntity<WeatherDataResponse> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!weatherDataService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar este dado climático.");
        }

        return ResponseEntity.ok(
                WeatherDataResponse.from(weatherDataService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Coletar dados climáticos", description = "Realiza uma consulta à API meteorológica e cria um novo registro de dados climáticos para a fazenda informada.")
    public ResponseEntity<WeatherDataResponse> postNew(
            @Valid @RequestBody WeatherDataRequest body,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!farmService.isOwner(body.getFarmId(), authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esta fazenda.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(WeatherDataResponse.from(
                        weatherDataService.newWeatherData(
                                body.getFarmId())));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover dado climático", description = "Remove um registro de dados climáticos específico, identificado pelo seu ID.")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        if (!weatherDataService.isOwner(id, authUser)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar este dado climático.");
        }

        weatherDataService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
