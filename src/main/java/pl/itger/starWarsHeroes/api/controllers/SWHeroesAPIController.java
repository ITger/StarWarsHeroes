/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Star Wars", description = "Star Wars API")
@OpenAPIDefinition(
        info = @Info(
                title = "Star Wars API",
                description = "Star Wars API"
        ))
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class SWHeroesAPIController implements SWHeroesAPI {

    private final SWHeroesAPIDelegate delegate;

    @Override public SWHeroesAPIDelegate getDelegate() {
        return delegate;
    }

}
