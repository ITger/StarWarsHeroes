/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.daos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa response z usługi 'SWHeroesAPIController Gwiezdnych Wojen'
 */
@Data
public class Hero {
    List<Starship> starships = new ArrayList<>();
    private String id;
    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private Homeworld homeworld;
}

