/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.itger.starWarsHeroes.api.daos.Hero;
import pl.itger.starWarsHeroes.api.daos.Page;

/**
 * http://localhost:8080/swagger-ui.html
 */
public interface SWHeroesAPI {

    SWHeroesAPIDelegate getDelegate();

    @Operation(summary = "Get a character by its id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Powodzenie / Success")
            , @ApiResponse(responseCode = "404", description = "Błędne żądanie / Bad request")
            , @ApiResponse(responseCode = "204", description = "Brak danych / no content")
    }) default ResponseEntity<Hero> getCharactersById(
            @Parameter(required = true, description = "id of character to be searched") @PathVariable int id) {
        return getDelegate().getCharactersById(id);
    }

    @Operation(summary = "Get a characters page")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Powodzenie / Success")
            , @ApiResponse(responseCode = "404", description = "Błędne żądanie / Bad request")
            , @ApiResponse(responseCode = "204", description = "Brak danych / no content")
    }) default ResponseEntity<Page> getCharactersPage(@RequestParam(value = "page", defaultValue = "1") int page) {
        return getDelegate().getCharactersPage(page);
    }

    @RequestMapping("/")
    default String home() {
        return "Hello Star Wars!";
    }
}
