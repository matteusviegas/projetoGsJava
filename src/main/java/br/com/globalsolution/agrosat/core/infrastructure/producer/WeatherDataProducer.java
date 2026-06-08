package br.com.globalsolution.agrosat.core.infrastructure.producer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.globalsolution.agrosat.messaging.event.NewWeatherDataEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherDataProducer {

    private final JmsTemplate jmsTemplate;

    public void publish(NewWeatherDataEvent event) {
        log.info("Enviando evento...");
        jmsTemplate.convertAndSend("agrosat.weather-data.queue", event);
    }

}
