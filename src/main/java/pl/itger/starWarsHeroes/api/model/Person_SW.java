/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * Klasa zawiera response z wywolania https://swapi.dev/api/people/_id_/
 */

/*
ponizsza konfiguracja lombok jest wymagana gdyz w innym przypadku jest wyjątek:
 com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
Cannot construct instance of `Person_SW` (no Creators, like
default construct, exist): cannot deserialize from Object value
(no delegate- or property-based Creator)

lombok tworzy encje immutable (private final jest niepotrzebny)
*/
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person_SW {
    String name;
    String height;
    String mass;
    String hair_color;
    String skin_color;
    String eye_color;
    String birth_year;
    String gender;
    String homeworld;
    List<String> films;
    List<String> species;
    List<String> vehicles;
    List<String> starships;
    String created;
    String edited;
    String url;
}
