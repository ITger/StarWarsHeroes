/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.model;

import lombok.*;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class Starship_SW {
    String name;
    String model;
    String manufacturer;
    String cost_in_credits;
    String length;
    String max_atmosphering_speed;
    String crew;
    String passengers;
    String cargo_capacity;
    String consumables;
    String hyperdrive_rating;
    String MGLT;
    String starship_class;
}

