package ch.puzzle.spring.workshop.beerio.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfiguration {
    @Bean
    ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder()
            //.featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }
}
