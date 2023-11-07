package com.revy.freewheelin.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "프리윌린 코딩 과제",
                description = "프리윌린 코딩 과제 API",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("프리윌린 코딩 과제")
                .pathsToMatch(paths)
                .build();
    }
}
