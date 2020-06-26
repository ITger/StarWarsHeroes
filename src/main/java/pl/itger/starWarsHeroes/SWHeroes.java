/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**

 */
@SpringBootApplication
@ComponentScan(basePackages = {"pl.itger.starWarsHeroes.api.controllers",
        "pl.itger.starWarsHeroes.api.repo",
        "pl.itger.starWarsHeroes.api.daos",
        "pl.itger.starWarsHeroes.api.model", "pl.itger.starWarsHeroes.api.exceptions"})
public class SWHeroes {
    public static void main(String[] args) {
        SpringApplication.run(SWHeroes.class, args);
    }

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
        return modelMapper;
    }
}
