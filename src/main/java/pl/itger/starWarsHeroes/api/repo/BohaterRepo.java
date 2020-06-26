/*
 * Copyright (c) 2020 Piotr Zerynger
 */

package pl.itger.starWarsHeroes.api.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.itger.starWarsHeroes.api.daos.Hero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class BohaterRepo {
    private Map<Long, Hero> longBohaterHashMap = new HashMap<>();


    public Page<Hero> getBohaterowie(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        List<Hero> result = longBohaterHashMap.values().stream().skip(toSkip).limit(pageable.getPageSize()).collect(toList());

        return new PageImpl<>(result, pageable, longBohaterHashMap.size());
    }

    public Optional<Hero> findById(long id) {
        return Optional.ofNullable(longBohaterHashMap.get(id));
    }
}

