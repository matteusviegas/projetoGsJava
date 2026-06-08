package br.com.globalsolution.agrosat.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Agrosat")
                        .version("1.0")
                        .description(
                                "Api construída durante a Global Solution de jun/2026 como parte do entregável da disciplina de java advanced."));

    }

}
