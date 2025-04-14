package com.carloso.ai.geminiapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info()
                .title("Gemini API")
                .description("API for interacting with Gemini AI models")
                .version("0.0.1")
                .contact(new Contact()
                    .name("Carlos O")
                    .email("carlosrolivera@gmail.com"))
                .license(new License()
                    .name("License")
                    .url("https://example.com")))
            .externalDocs(new ExternalDocumentation()
                .description("Project Documentation")
                .url("https://example.com/docs"));
    }
}