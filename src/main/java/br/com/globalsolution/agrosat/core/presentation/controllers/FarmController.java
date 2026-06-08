package br.com.globalsolution.agrosat.core.presentation.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.EntityModel;
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

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.User;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.core.presentation.dto.request.Farm.CreateFarmRequest;
import br.com.globalsolution.agrosat.core.presentation.dto.request.Farm.UpdateFarmRequest;
import br.com.globalsolution.agrosat.core.presentation.dto.response.Farm.FarmResponse;
import br.com.globalsolution.agrosat.core.presentation.dto.response.Plantation.PlantationResponse;
import br.com.globalsolution.agrosat.core.presentation.dto.response.WeatherData.WeatherDataResponse;
import br.com.globalsolution.agrosat.core.service.Farm.FarmService;
import br.com.globalsolution.agrosat.core.service.Plantation.PlantationService;
import br.com.globalsolution.agrosat.core.service.WeatherData.WeatherDataService;
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

        private final PlantationService plantationService;

        private final WeatherDataService weatherDataService;

        @GetMapping("/{id}")
        @Operation(summary = "Buscar fazenda por ID", description = "Retorna os dados de uma fazenda específica, identificada pelo seu ID.")
        public ResponseEntity<EntityModel<FarmResponse>> getById(
                        @PathVariable Long id,
                        @AuthenticationPrincipal JwtUserData authUser) {

                if (!farmService.isOwner(id, authUser)) {
                        throw new ResponseStatusException(
                                        HttpStatus.FORBIDDEN,
                                        "Você não tem permissão para acessar esta fazenda.");
                }

                Farm farm = farmService.findById(id);

                EntityModel<FarmResponse> model = EntityModel.of(FarmResponse.from(farm));

                model.add(linkTo(methodOn(FarmController.class).getById(id, null)).withSelfRel());
                model.add(linkTo(methodOn(FarmController.class).getPlantationsByFarmId(id, null))
                                .withRel("plantations"));
                model.add(linkTo(methodOn(FarmController.class).getWeatherDatasByFarmId(id, null))
                                .withRel("weather-datas"));

                return ResponseEntity.ok(model);
        }

        @GetMapping("/{id}/plantations")
        @Operation(summary = "Listar plantações da fazenda", description = "Retorna todas as plantações associadas a uma fazenda específica, identificada pelo seu ID.")
        public ResponseEntity<List<PlantationResponse>> getPlantationsByFarmId(
                        @PathVariable Long id,
                        @AuthenticationPrincipal JwtUserData authUser) {

                if (!farmService.isOwner(id, authUser)) {
                        throw new ResponseStatusException(
                                        HttpStatus.FORBIDDEN,
                                        "Você não tem permissão para acessar estas plantações.");
                }

                Farm farm = farmService.findById(id);

                return ResponseEntity.ok(
                                plantationService.findAllByFarm(farm)
                                                .stream()
                                                .map(PlantationResponse::from)
                                                .toList());
        }

        @GetMapping("/{id}/weather-datas")
        @Operation(summary = "Listar dados climáticos da fazenda", description = "Retorna todos os dados climáticos associados a uma fazenda específica, identificada pelo seu ID.")
        public ResponseEntity<List<WeatherDataResponse>> getWeatherDatasByFarmId(
                        @PathVariable Long id,
                        @AuthenticationPrincipal JwtUserData authUser) {

                if (!farmService.isOwner(id, authUser)) {
                        throw new ResponseStatusException(
                                        HttpStatus.FORBIDDEN,
                                        "Você não tem permissão para acessar estes dados climáticos.");
                }

                Farm farm = farmService.findById(id);

                return ResponseEntity.ok(
                                weatherDataService.findAllByFarm(farm)
                                                .stream()
                                                .map(WeatherDataResponse::from)
                                                .toList());
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
