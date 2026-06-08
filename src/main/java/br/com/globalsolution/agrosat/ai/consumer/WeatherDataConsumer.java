package br.com.globalsolution.agrosat.ai.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.globalsolution.agrosat.messaging.event.NewWeatherDataEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherDataConsumer {

    @JmsListener(destination = "agrosat.weather-data.queue")
    public void consume(NewWeatherDataEvent event) {
        log.info(
                "Evento climático recebido. Fazenda: {}, temperatura: {}",
                event.farm().name(),
                event.temperature());
    }

}
