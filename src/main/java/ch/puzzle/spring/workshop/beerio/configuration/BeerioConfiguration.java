package ch.puzzle.spring.workshop.beerio.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "beerio")
@Data
public class BeerioConfiguration {
    private String favoriteBeer;
}
