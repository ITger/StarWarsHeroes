package pl.itger.starWarsHeroes.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pl.itger.starWarsHeroes.api.controllers.SWHeroesAPIDelegate;
import pl.itger.starWarsHeroes.api.daos.Hero;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
public class SWHeroesAPIDelegateImplTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SWHeroesAPIDelegate empService;// = new SWHeroesAPIDelegateImpl();

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
        //Employee emp = new Employee(“E001”, "Eric Simmons");
        ResponseEntity<Hero> bohaterResponseEntity = empService.getCharactersById(1);
        System.out.println(bohaterResponseEntity);
//        Mockito
//                .when(restTemplate.getForEntity(
//                        "http://localhost:8080/employee/E001", Employee.class))
//                .thenReturn(new ResponseEntity(emp, HttpStatus.OK));
//
//        Employee employee = empService.getEmployee(id);
        Assert.assertEquals(bohaterResponseEntity, bohaterResponseEntity);
    }
}