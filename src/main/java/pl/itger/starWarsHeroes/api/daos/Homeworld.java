package pl.itger.starWarsHeroes.api.daos;

import lombok.Data;

/**
 * Klasa response z usługi 'SWHeroesAPIController Gwiezdnych Wojen'
 */
@Data
public class Homeworld {
    public String name;
    public String rotationPeriod;
    public String orbitalPeriod;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    public String surfaceWater;
    public String population;
}
