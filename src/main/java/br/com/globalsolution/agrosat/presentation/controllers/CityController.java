package br.com.globalsolution.agrosat.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalsolution.agrosat.presentation.dto.response.City.CityResponse;
import br.com.globalsolution.agrosat.service.City.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/cities")
@Tag(name = "Cidades", description = "Endpoints responsáveis pela consulta das cidades da plataforma AgroSat.")
public class CityController {

    private final CityService cityService;

    @GetMapping
    @Operation(summary = "Listar cidades", description = "Retorna todas as cidades cadastradas na plataforma.")
    public ResponseEntity<List<CityResponse>> getAll() {
        return ResponseEntity.ok(
                cityService.findAll()
                        .stream()
                        .map(CityResponse::from)
                        .toList());
    }

}
