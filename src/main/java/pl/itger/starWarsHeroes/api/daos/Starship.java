package pl.itger.starWarsHeroes.api.daos;

import lombok.Data;

/**
 * Klasa response z usługi 'SWHeroesAPIController Gwiezdnych Wojen'
 */
@Data
public class Starship {
    private String name;
    private String model;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;
    private String cargoCapacity;
    private String consumables;
    private String hyperdriveRating;
    private String MGLT;
    private String starshipClass;
}
