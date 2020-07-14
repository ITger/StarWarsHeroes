package pl.itger.starWarsHeroes.api.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.itger.starWarsHeroes.api.daos.Hero;
import pl.itger.starWarsHeroes.api.daos.Page;

import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * testy integracyjne.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeroesControllerTest extends AbstractTest {
    private static final Logger LOG = Logger.getLogger(HeroesControllerTest.class.getName());

    @Override
    @BeforeAll protected void setUp() {
        super.setUp();
    }

    @Test
    public void getCharacterLukeSkywalker_test() throws Exception {
        LOG.info("looking for Luke Skywalker");
        String uri = "/characters/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Hero hero = super.mapFromJson(content, Hero.class);
        assertEquals("1", hero.getId());
        assertEquals("Luke Skywalker", hero.getName());
        assertEquals("Tatooine", hero.getHomeworld().name);
        assertEquals(2, hero.getStarships().size());
    }

    @Test
    public void getCharacterC_3PO_test() throws Exception {
        LOG.info("looking for C_3PO");
        String uri = "/characters/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Hero hero = super.mapFromJson(content, Hero.class);
        assertEquals("2", hero.getId());
        assertEquals("C-3PO", hero.getName());
    }

    @Test
    public void getCharacterStatus404() throws Exception {
        LOG.info("404 expected");
        String uri = "/characters/9999";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        Hero hero = super.mapFromJson(content, Hero.class);
        assertEquals("9999", hero.getId());
        assertNull(hero.getName());
    }


    @Test
    public void getPage_1() throws Exception {
        LOG.info("200 expected");
        String uri = "/characters?page=1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Page page = super.mapFromJson(content, Page.class);
        assertNotNull(page);
        assertEquals(10, page.getElements().size());
    }

    @Test
    public void getPage_100() throws Exception {
        LOG.info("404 expected");
        String uri = "/characters?page=100";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
}

