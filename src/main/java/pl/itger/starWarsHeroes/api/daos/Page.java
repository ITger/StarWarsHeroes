package pl.itger.starWarsHeroes.api.daos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa response z usługi 'SWHeroesAPIController Gwiezdnych Wojen'
 */
@Data
public class Page {
    Integer count;
    Integer pages;
    List<Hero> elements = new ArrayList<>();
}
