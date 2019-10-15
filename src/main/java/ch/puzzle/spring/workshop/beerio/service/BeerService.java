package ch.puzzle.spring.workshop.beerio.service;

import ch.puzzle.spring.workshop.beerio.model.Beer;
import ch.puzzle.spring.workshop.beerio.model.BeerRepository;
import java.time.Duration;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.FluxSink.OverflowStrategy;

@Service
@Slf4j
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

    private FluxProcessor<Beer, Beer> processor;
    private FluxSink<Beer> sink;

    private Beer find(long id) {
        return beerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Beer not found"));
    }

    public Flux<Beer> streamOfBeers() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
        Flux<Beer> beerFlux = Flux.fromIterable(findAll());
        return Flux.zip(beerFlux, interval, (beer, tick) -> beer);
    }

    @PostConstruct
    void init() {
        processor = DirectProcessor.<Beer>create().serialize();
        sink = processor.sink(OverflowStrategy.BUFFER);
    }

    public void recommendBeer(long id) {
        sink.next(find(id));
    }

    public Flux<Beer> beerRecommendations() {
        return processor;
    }
}
