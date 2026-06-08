package br.com.globalsolution.agrosat.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalsolution.agrosat.presentation.dto.response.CropType.CropTypeResponse;
import br.com.globalsolution.agrosat.service.CropType.CropTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/crop-types")
@Tag(name = "Tipos de Cultura", description = "Endpoints responsáveis pela consulta dos tipos de cultura da plataforma AgroSat.")
public class CropTypeController {

    private final CropTypeService cropTypeService;

    @GetMapping
    @Operation(summary = "Listar tipos de cultura", description = "Retorna todos os tipos de cultura cadastrados na plataforma.")
    public ResponseEntity<List<CropTypeResponse>> getAll() {
        return ResponseEntity.ok(
                cropTypeService.findAll()
                        .stream()
                        .map(CropTypeResponse::from)
                        .toList());
    }

}
