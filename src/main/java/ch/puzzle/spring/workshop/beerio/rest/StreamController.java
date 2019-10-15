package ch.puzzle.spring.workshop.beerio.rest;

import ch.puzzle.spring.workshop.beerio.model.Beer;
import ch.puzzle.spring.workshop.beerio.service.BeerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/stream")
public class StreamController {

  @Autowired
  BeerService beerService;

  @GetMapping(value = "beers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ApiOperation(value = "streams a beer all 3 seconds", notes = "```new EventSource('/api/v1/stream/beers').addEventListener('beer-received', beer => console.log(beer))```")
  public Flux<ServerSentEvent<Beer>> beerAnnouncer() {
    return beerService.streamOfBeers().map(beer -> ServerSentEvent
        .builder(beer)
        .event("beer-received")
        .id(String.valueOf(beer.getId()))
        .build());
  }

  @GetMapping(value = "recommendations", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ApiOperation(value = "user recommended beers as a stream", notes = "```new EventSource('/api/v1/stream/recommendations').addEventListener('beer-received', beer => console.log(beer))```")
  public Flux<ServerSentEvent<Beer>> recommendationsStream() {
    return beerService.beerRecommendations().map(beer -> ServerSentEvent
        .builder(beer)
        .event("beer-received")
        .id(String.valueOf(beer.getId()))
        .build());
  }

}
