package ch.puzzle.spring.workshop.beerio.service;

import ch.puzzle.spring.workshop.beerio.model.Beer;
import ch.puzzle.spring.workshop.beerio.model.BeerRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryService {
    @Autowired
    BeerRepository beerRepository;

  @Cacheable("countries")
  public Iterable<String> findAllCountries() {
    log.debug("Building country list...");
    List<String> countries = StreamSupport.stream(beerRepository.findAll().spliterator(), false)
        .map(Beer::getCountry)
        .distinct()
        .collect(Collectors.toList());
    log.debug("Done building country list.");
    return countries;
  }
}
