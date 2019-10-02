package ch.puzzle.spring.workshop.beerio.rest;

import ch.puzzle.spring.workshop.beerio.model.Beer;
import ch.puzzle.spring.workshop.beerio.service.BeerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping
    @ApiOperation("Returns all beers, might crash your browser!")
    public Iterable<Beer> findAll() {
        return beerService.findAll();
    }

    @GetMapping("{country}")
    @ApiOperation("Returns all beers from a specific country")
    public Iterable<Beer> findAllByCountry(
            @PathVariable @ApiParam(value = "Country to filter", example = "Switzerland", required = true) String country
    ) {
        return beerService.findAllByCountry(country);
    }
}
