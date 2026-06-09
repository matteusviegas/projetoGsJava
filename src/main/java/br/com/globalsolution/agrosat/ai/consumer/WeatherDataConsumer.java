package br.com.globalsolution.agrosat.ai.consumer;

import java.time.LocalDate;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.globalsolution.agrosat.ai.domainmodel.AiRecommendation;
import br.com.globalsolution.agrosat.ai.service.AiRecommendation.AiRecommendationService;
import br.com.globalsolution.agrosat.messaging.event.NewWeatherDataEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherDataConsumer {

    private final AiRecommendationService aiRecommendationService;

    @JmsListener(destination = "agrosat.weather-data.queue")
    public void consume(NewWeatherDataEvent event) {
        log.info(
                "Evento climático recebido. Fazenda: {}, temperatura: {}",
                event.farm().name(), event.temperature());

        aiRecommendationService.create(AiRecommendation.builder()
                .recommendation("Recomendação de teste")
                .priority(1)
                .recommendationDate(LocalDate.now())
                .status("A")
                .plantationId(event.plantations().getFirst().plantationId())
                .build());
    }

}
