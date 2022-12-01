package be.abis.cllientsandwich.services;

import be.abis.cllientsandwich.exception.PersonNotFoundException;
import be.abis.cllientsandwich.exception.SessionNotFoundException;
import be.abis.cllientsandwich.service.PersonService;
import be.abis.cllientsandwich.service.SessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SessionServiceTest {
    //hasn't been tested after databases

    @Autowired
    SessionService sessionService;

    @Test
    public void getALL(){


        assertEquals(4,sessionService.getSessions().size());

    }

    @Test
    public void findByName() throws PersonNotFoundException, JsonProcessingException, SessionNotFoundException {

        assertEquals("SQL",sessionService.findMostRecentSession("SQL").getName());


    }
    @Test
    public void findByNamefalse() throws PersonNotFoundException, JsonProcessingException {
        assertThrows(SessionNotFoundException.class,()->sessionService.findMostRecentSession("SQL4654sdf").getName());

    }
// to do
    @Test
    public void add() throws PersonNotFoundException, JsonProcessingException {
        assertThrows(SessionNotFoundException.class,()->sessionService.findMostRecentSession("SQL4654sdf").getName());

    }
    @Test
    public void addexisting() throws PersonNotFoundException, JsonProcessingException {
        assertThrows(SessionNotFoundException.class,()->sessionService.findMostRecentSession("SQL4654sdf").getName());

    }
}
