package ch.puzzle.spring.workshop.beerio;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.puzzle.spring.workshop.beerio.logging.LoggingAspect;
import ch.puzzle.spring.workshop.beerio.model.Beer;
import ch.puzzle.spring.workshop.beerio.model.BeerRepository;
import ch.puzzle.spring.workshop.beerio.service.BeerService;
import ch.puzzle.spring.workshop.beerio.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = {LoggingAspect.class, AnnotationAwareAspectJAutoProxyCreator.class, BeerService.class, CountryService.class},
    properties = "test=bla")
public class BeerioTests {

  @Autowired
  BeerService beerService;

  @Autowired
  CountryService countryService;

  @MockBean
  BeerRepository beerRepository;

  @Value("test")
  String test;

  @Test
  public void beerServiceTest() {
    when(beerRepository.findAll())
        .thenReturn(singleton(Beer.builder().name("Bärner Müntschi").country("Schweiz").build()));

    assertThat(beerService.findAll())
        .hasSize(1)
        .extracting(Beer::getName)
        .containsExactly("Bärner Müntschi");

    assertThat(test).isEqualTo("test");

    verify(beerRepository, times(1)).findAll();
  }

  @Test
  public void countryServiceTest() {
    when(beerRepository.findAll())
        .thenReturn(singleton(Beer.builder().name("Bärner Müntschi").country("Schweiz").build()));

    assertThat(countryService.findAllCountries()).hasSize(1);
  }
}
