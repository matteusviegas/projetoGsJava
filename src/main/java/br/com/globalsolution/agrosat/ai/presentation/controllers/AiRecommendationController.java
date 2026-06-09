package br.com.globalsolution.agrosat.ai.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalsolution.agrosat.ai.presentation.dto.response.AiRecommendationResponse;
import br.com.globalsolution.agrosat.ai.service.AiRecommendation.AiRecommendationService;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ai-recommendations")
@Tag(name = "AI Recommendations", description = "Endpoints responsáveis pelo gerenciamento das recomendações geradas pela inteligência artificial com base em dados meteorológicos e informações das plantações.")
public class AiRecommendationController {

    private final AiRecommendationService aiRecommendationService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar recomendação por ID", description = "Retorna os dados de uma recomendação gerada pela inteligência artificial a partir do seu identificador.")
    public ResponseEntity<AiRecommendationResponse> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        return ResponseEntity.ok(
                AiRecommendationResponse.from(aiRecommendationService.findById(id)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover recomendação", description = "Remove uma recomendação gerada pela inteligência artificial a partir do seu identificador.")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtUserData authUser) {

        aiRecommendationService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
