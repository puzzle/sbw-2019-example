package ch.puzzle.spring.workshop.beerio.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Long> {
    Iterable<Beer> findAllByCountryIgnoreCase(String country);
}
