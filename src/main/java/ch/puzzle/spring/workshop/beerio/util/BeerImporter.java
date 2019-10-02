package ch.puzzle.spring.workshop.beerio.util;

import ch.puzzle.spring.workshop.beerio.model.Beer;
import ch.puzzle.spring.workshop.beerio.model.BeerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeerImporter {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Value("classpath:beers.json")
    Resource beersResource;

    @PostConstruct
    public void importBeers() throws IOException {
        Beer[] beers = objectMapper.readValue(beersResource.getInputStream(), Beer[].class);
        List<Beer> collect = Arrays.stream(beers)
                .collect(Collectors.toList());
        beerRepository.saveAll(collect);
    }
}
