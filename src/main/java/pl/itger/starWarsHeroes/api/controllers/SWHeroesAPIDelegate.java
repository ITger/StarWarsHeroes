/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.itger.starWarsHeroes.api.daos.Hero;
import pl.itger.starWarsHeroes.api.daos.Page;

public interface SWHeroesAPIDelegate {

    /**
     * @see SWHeroesAPI#getCharactersById(int)
     */
    default ResponseEntity<Hero> getCharactersById(int id) {
        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    /**
     * @see SWHeroesAPI#getCharactersPage(int)
     */
    default ResponseEntity<Page> getCharactersPage(int page) {
        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }
}
