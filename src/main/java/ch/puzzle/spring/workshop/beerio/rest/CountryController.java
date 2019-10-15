package ch.puzzle.spring.workshop.beerio.rest;

import ch.puzzle.spring.workshop.beerio.service.CountryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping
    @ApiOperation("Returns all countries")
    @Cacheable(cacheNames = "countries")
    public Iterable<String> findAll() {
        return countryService.findAllCountries();
    }

}
