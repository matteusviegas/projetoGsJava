package br.com.globalsolution.agrosat.ai.service.AiRecommendation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.ai.domainmodel.AiRecommendation;
import br.com.globalsolution.agrosat.ai.domainmodel.repository.AiRecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiRecommendationServiceImpl implements AiRecommendationService {

    private final AiRecommendationRepository aiRecommendationRepository;

    @Override
    public AiRecommendation findById(Long id) {
        return aiRecommendationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Recomendação de IA não encontrada"));
    }

    @Override
    public AiRecommendation create(AiRecommendation o) {
        AiRecommendation aiRecommendation = aiRecommendationRepository.save(o);

        log.info("Nova recomendação de IA criada: " + aiRecommendation.getRecommendationId());
        return aiRecommendation;
    }

    @Override
    public void removeById(Long id) {
        findById(id);
        aiRecommendationRepository.deleteById(id);
    }

}

// private final RagQueryService ragQueryService;

// private final ObjectMapper objectMapper;

// private AiRecommendation createForPlantation(
// NewWeatherDataEvent event,
// PlantationEventDTO plantation) {

// try {
// String question = buildQuestion(event, plantation);

// String response = ragQueryService.ask(question);

// AiRecommendationGeneratedDTO dto = objectMapper.readValue(
// response,
// AiRecommendationGeneratedDTO.class);

// AiRecommendation recommendation = AiRecommendation.builder()
// .recommendation(dto.recommendation())
// .priority(dto.priority())
// .recommendationDate(LocalDate.now())
// .status("A")
// .plantationId(plantation.plantationId())
// .build();

// return aiRecommendationRepository.save(recommendation);

// } catch (Exception exception) {
// throw new ResponseStatusException(
// HttpStatus.INTERNAL_SERVER_ERROR,
// "Erro ao gerar recomendação para a plantação "
// + plantation.name());
// }
// }

// private String buildQuestion(
// NewWeatherDataEvent event,
// PlantationEventDTO plantation) {

// return """
// Gere uma recomendação agrícola para a plantação abaixo.

// Fazenda:
// - Nome: %s
// - Cidade: %s
// - Estado: %s
// - Área total: %s hectares
// - Latitude: %s
// - Longitude: %s

// Plantação:
// - Nome: %s
// - Cultura: %s
// - Status: %s
// - Data de plantio: %s
// - Área plantada: %s hectares

// Dados climáticos:
// - Temperatura: %s °C
// - Umidade: %s %%
// - Velocidade do vento: %s m/s
// - Descrição: %s

// Responda exclusivamente em JSON válido no formato:
// {
// "recommendation": "texto da recomendação",
// "priority": 1
// }

// Prioridade:
// 1 = BAIXA
// 2 = MÉDIA
// 3 = ALTA
// """
// .formatted(
// event.farm().name(),
// event.farm().cityName(),
// event.farm().stateName(),
// event.farm().totalArea(),
// event.farm().latitude(),
// event.farm().longitude(),

// plantation.name(),
// plantation.cropTypeName(),
// plantation.statusName(),
// plantation.plantingDate(),
// plantation.plantedArea(),

// event.temperature(),
// event.humidity(),
// event.windSpeed(),
// event.description());
// }
