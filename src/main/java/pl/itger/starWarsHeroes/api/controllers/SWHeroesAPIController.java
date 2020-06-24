package pl.itger.starWarsHeroes.api.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Tag(name = "Star Wars", description = "SWHeroesAPIController Gwiezdnych Wojen")
@OpenAPIDefinition(
        info = @Info(
                title = "Star Wars API",
                description = "SWHeroesAPIController Gwiezdnych Wojen (oraz ich sprzęcior) "
        ))
@RestController
@RequestMapping("/characters")
public class SWHeroesAPIController implements SWHeroesAPI {

    private static final Logger LOG = Logger.getLogger(SWHeroesAPIController.class.getName());
    private final SWHeroesAPIDelegate delegate;

    @Autowired
    public SWHeroesAPIController(SWHeroesAPIDelegateImpl delegate) {
        this.delegate = delegate;
    }

    @Override public SWHeroesAPIDelegate getDelegate() {
        return delegate;
    }

}
