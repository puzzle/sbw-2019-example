package ch.puzzle.spring.workshop.beerio.service;

import ch.puzzle.spring.workshop.beerio.model.Beer;
import ch.puzzle.spring.workshop.beerio.model.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {
    @Autowired
    BeerRepository beerRepository;

    public Iterable<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Iterable<Beer> findAllByCountry(String country) {
        return beerRepository.findAllByCountryIgnoreCase(country);
    }

    public Iterable<Beer> findTop10OrderByAlcoholPercent() {
        return beerRepository.findTop10ByOrderByAlcoholPercentDesc();
    }
}
