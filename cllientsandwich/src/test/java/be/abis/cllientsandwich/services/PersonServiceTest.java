package be.abis.cllientsandwich.services;


import be.abis.cllientsandwich.exception.PersonExistsException;
import be.abis.cllientsandwich.exception.PersonNotFoundException;
import be.abis.cllientsandwich.model.Person;
import be.abis.cllientsandwich.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    PersonService personService;

    @Test
    public void getALL(){


        assertEquals(4,personService.getPersonList().size());

    }

    @Test
    public void findByName() throws PersonNotFoundException, JsonProcessingException {

        assertEquals("Jietse",personService.findPerson("Jietse","Molenaers").getFirstName());


    }

    @Test
    public void findByNameFail() throws PersonNotFoundException, JsonProcessingException {

        assertThrows(PersonNotFoundException.class,()->personService.findPerson("sdf","Molenaers").getFirstName());


    }

    @Test
    public void findById() throws PersonNotFoundException, JsonProcessingException {

        assertEquals("Joske",personService.findPerson(1).getFirstName());


    }
    @Test
    public void findByNamefalse() throws PersonNotFoundException, JsonProcessingException {
        assertThrows(PersonNotFoundException.class,()->personService.findPerson(90809));

    }

    @Test
    public void addAndRemove() throws PersonNotFoundException, JsonProcessingException, PersonExistsException {
        Person p=new Person("simon","haas");
        personService.addPerson(p);

        personService.findPerson("simon","haas");
        personService.removePerson(   personService.findPerson("simon","haas"));

    }

    @Test
    public void addExistingId() throws PersonNotFoundException, JsonProcessingException, PersonExistsException {
        Person p=new Person(2,"simon","haas");


        assertThrows(PersonExistsException.class,()->        personService.addPerson(p));

    }

    @Test
    public void addExistingName() throws PersonNotFoundException, JsonProcessingException, PersonExistsException {
        Person p=new Person(99908,"Jietse","Molenaers");


        assertThrows(PersonExistsException.class,()->        personService.addPerson(p));

    }

    @Test
    public void RemoveNonExist() throws PersonNotFoundException, JsonProcessingException, PersonExistsException {
        Person p=new Person("simond","Molenaers");
        assertThrows(PersonNotFoundException.class,()->        personService.removePerson(p));

    }
}
