package ch.puzzle.spring.workshop.beerio.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, Long> {
    Iterable<Beer> findAllByCountryIgnoreCase(String country);

    Iterable<Beer> findTop10ByOrderByAlcoholPercentDesc();

}
