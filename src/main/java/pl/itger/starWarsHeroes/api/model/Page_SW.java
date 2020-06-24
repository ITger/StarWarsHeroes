package pl.itger.starWarsHeroes.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;


/**
 * Klasa zawiera response z https://swapi.dev/api/people/?page=X,
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page_SW {
    Integer count;
    String next;
    String previous;
    List<Person_SW> results;
    String url;
}
