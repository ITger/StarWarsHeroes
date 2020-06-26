/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.itger.starWarsHeroes.api.daos.Hero;
import pl.itger.starWarsHeroes.api.daos.Homeworld;
import pl.itger.starWarsHeroes.api.daos.Page;
import pl.itger.starWarsHeroes.api.daos.Starship;
import pl.itger.starWarsHeroes.api.model.Page_SW;
import pl.itger.starWarsHeroes.api.model.Person_SW;
import pl.itger.starWarsHeroes.api.model.Planet_SW;
import pl.itger.starWarsHeroes.api.model.Starship_SW;
import pl.itger.starWarsHeroes.api.repo.BohaterRepo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SWHeroesAPIDelegateImpl implements SWHeroesAPIDelegate {
    private static final Logger LOG = Logger.getLogger(SWHeroesAPIDelegateImpl.class.getName());

    //TODO: uzyc org.springframework.web.reactive.client.WebClient do rownolegego pobrania planety i starship dla danej persony.
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BohaterRepo repository;

    @Autowired
    private ModelMapper modelMapper;

    final static String HTTP = "http";
    final static String HTTPS = "https";

    public ResponseEntity<Hero> getCharactersById(
            @Parameter(required = true, description = "id of character to be searched") @PathVariable int id) {
        LOG.info("getCharactersById " + id);
        RestTemplate restTemplate = new RestTemplate();
        Person_SW person_sw; //TODO: make optional
        Hero hero;
        try {
            person_sw = restTemplate.getForObject("https://swapi.dev/api/people/" + id + "/", Person_SW.class);
            if (person_sw == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (RestClientException exc) {
            LOG.log(Level.SEVERE, exc.getMessage(), exc);
            hero = new Hero();
            hero.setId(String.valueOf(id));
            return new ResponseEntity<>(hero, HttpStatus.NOT_FOUND);
        }
        hero = convertToDto(person_sw, Hero.class);
        hero.setId(String.valueOf(id));
        Optional<String> optHomeWorld = Optional.of(person_sw.getHomeworld());
        optHomeWorld.ifPresent(hw -> {
            Planet_SW planet_sw = restTemplate.getForObject(hw.replace(HTTP, HTTPS), Planet_SW.class);
            Homeworld homeworld = convertToDto(planet_sw, Homeworld.class);
            hero.setHomeworld(homeworld);
        });
        List<Starship> starshipList = new ArrayList<>(person_sw.getStarships().size());
        person_sw.getStarships().forEach(ship -> {
            Starship_SW s = restTemplate.getForObject(ship.replace(HTTP, HTTPS), Starship_SW.class);
            Starship starSh = convertToDto(s, Starship.class);
            starshipList.add(starSh);
        });
        hero.setStarships(starshipList);
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    public ResponseEntity<Page> getCharactersPage(@RequestParam(value = "page", defaultValue = "1") int page) {
        LOG.info("getCharactersPage " + page);
        RestTemplate restTemplate = new RestTemplate();
        Page_SW page_sw;
        final Page pageDTO = new Page();
        try {
            String uri = "https://swapi.dev/api/people/?page=".concat(String.valueOf(page));
            LOG.info(uri);
            page_sw = restTemplate.getForObject(uri, Page_SW.class);
            if (page_sw == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (RestClientException exc) {
            LOG.log(Level.SEVERE, exc.getMessage(), exc);
            return new ResponseEntity<>(pageDTO, HttpStatus.NOT_FOUND);
        }
        page_sw.getResults().forEach(person_sw -> {
            Hero hero = convertToDto(person_sw, Hero.class);
            List<Starship> starshipList = new ArrayList<>();
            person_sw.getStarships().forEach(ship -> {
                Starship_SW s = restTemplate.getForObject(ship.replace(HTTP, HTTPS), Starship_SW.class);
                starshipList.add(convertToDto(s, Starship.class));
            });
            hero.setStarships(starshipList);
            Planet_SW planet_sw = restTemplate.getForObject(person_sw.getHomeworld().replace(HTTP, HTTPS), Planet_SW.class);
            Homeworld homeworld = convertToDto(planet_sw, Homeworld.class);
            hero.setHomeworld(homeworld);
            hero.setId(getIdFromUrl(person_sw.getUrl()));
            pageDTO.getElements().add(hero);
        });
        pageDTO.setCount(page_sw.getCount());
        pageDTO.setPages(9);
        return new ResponseEntity(pageDTO, HttpStatus.OK);
    }

    /**
     * mapuje typy przychodzace z https://swapi.dev/ na typy dao zgodny z zadaniem rekrutacyjnym.
     * <p>
     * TODO: moze utworzyc osobna klase typu utils? ale dla jednej metody czy warto?
     *
     * @param i    obiekt wejsciowy
     * @param type klasa typu wyjsciowego
     * @param <I>  typ we
     * @param <O>  typ wy
     * @return dto typu O
     */
    private <I, O> O convertToDto(I i, Class<O> type) {
        return modelMapper.map(i, type);
    }

    @SneakyThrows private String getIdFromUrl(String s){
        URL url = new URL(s);
        String path = url.getPath();
        String[] pairs = path.split("/");
        //TODO: check if id is a Number.
        return pairs[pairs.length-1];
    }
}
