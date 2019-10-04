package ch.puzzle.spring.workshop.beerio;

import ch.puzzle.spring.workshop.beerio.configuration.BeerioConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeerioApplication {
    @Autowired
    BeerioConfiguration beerioConfiguration;

	public static void main(String[] args) {
        SpringApplication.run(BeerioApplication.class, args);
	}

}
