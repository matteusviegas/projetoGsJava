package br.com.globalsolution.agrosat.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableJms
public class JmsConfig {

    public static final String SENSOR_READING_QUEUE = "agrosat.sensor-reading.queue";

    @Bean
    MappingJackson2MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
