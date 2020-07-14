package pl.itger.starWarsHeroes.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.itger.starWarsHeroes.SWHeroes;
import pl.itger.starWarsHeroes.api.controllers.SWHeroesAPIController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SWHeroes.class)
@WebAppConfiguration
public class SWHeroesAPIControllerTest {

    @Autowired
    SWHeroesAPIController bohaterowieAPIController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contexLoads() throws Exception {
        assertThat(bohaterowieAPIController).isNotNull();
    }

    @Test
    public void charactersPage_1() throws Exception {
        //http://localhost:8080/characters?page=1
        //Luke Skywalker
        assertThat(this.restTemplate.getForObject("http://localhost:8080/characters?page=1",
                String.class)).contains("Luke Skywalker");
    }

    @Test
    public void charactersId_2() throws Exception {
        //http://localhost:8080/characters/2
        //C-3PO
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
//                String.class)).contains("Hello, World");
    }
}
