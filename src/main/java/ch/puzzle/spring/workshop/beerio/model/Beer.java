package ch.puzzle.spring.workshop.beerio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beer {
    @Id
    private long id;
    private Double alcoholPercent;
    private String address;
    private String category;
    private Double lat;
    private Double lon;
    private String country;

    @Lob
    private String description;
    private Integer ibu;
    private String name;
    private String brewery;
    private Integer srm;
    private String style;
}
