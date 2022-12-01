package be.abis.clientrest.cliet;

import be.abis.clientrest.exception.ApiException;
import be.abis.clientrest.exception.ValidationException;
import be.abis.clientrest.exception.PersonAlreadyExistsException;
import be.abis.clientrest.exception.PersonNotFoundException;
import be.abis.clientrest.model.Address;
import be.abis.clientrest.model.Company;
import be.abis.clientrest.model.Person;
import be.abis.clientrest.service.PersonService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class clientTest {

    @Autowired
    private RestTemplate rt;
    @Autowired
    PersonService personService;
    String baseUrl="http://localhost:8080/exercise/personapi/persons";
    @Test
    public void testFindGuestById() throws JsonProcessingException, PersonNotFoundException {
       Person g=personService.findPerson(1);
        assertEquals("John",g.getFirstName());
    }

    @Test
    public void testFindGuestByIdwrong() throws JsonProcessingException, PersonNotFoundException {
        Person g=personService.findPerson(1);
        assertEquals("John",g.getFirstName());
    }

    @Test
    public void getALL(){
        System.out.println(personService.getAllPersons().size());

    }

    @Test
    @Order(2)
    public void addperson() throws IOException, PersonNotFoundException, PersonAlreadyExistsException, ValidationException {
        Address a=new Address("lou",7,"3000","leuv");
        Company c=new Company("goog","01148788","84",a);

        Person p=new Person(12,"sim","haas", LocalDate.of(1997,9,3),"sifsdm@gmail.com","pasdsas","nl",c);
        personService.addPerson(p);


    }

    @Test

    public void addpersonshortpass() throws IOException, PersonNotFoundException, PersonAlreadyExistsException, ValidationException {
        Address a=new Address("lou",7,"3000","leuv");
        Company c=new Company("goog","01148788","84",a);

        Person p=new Person(12,"sim","haas", LocalDate.of(1997,9,3),"sdm@","paas","nl",c);
        assertThrows(ValidationException.class,()->        personService.addPerson(p));


    }
    @Test

    public void addpersonthatExists() throws IOException, PersonNotFoundException, PersonAlreadyExistsException {
        Person p=personService.findPerson(1);
        assertThrows(PersonAlreadyExistsException.class,()->personService.addPerson(p));


    }
    @Test
    @Order(1)
    public void deleteperson() throws PersonNotFoundException, JsonProcessingException {
        personService.deletePerson(12);

    }

    @Test

    public void deletepersonnonExist() throws PersonNotFoundException, JsonProcessingException {
        assertThrows(PersonNotFoundException.class, ()->personService.deletePerson(88));

    }
    @Test
    public void changepass() throws IOException, PersonNotFoundException, ValidationException, ApiException {
        Person p= personService.findPerson(1);

        personService.changePassword(p,"jabadoe12jlkj","api1");

    }
    @Test
    public void getBycompanyName(){
        System.out.println(personService.findPersonsByCompanyName("Abis"));

    }
    @Test
    public void getBymailAndPass() throws PersonNotFoundException, JsonProcessingException {
        String email="mjones@abis.be";
        String pass="abc123";

        System.out.println(personService.findPerson(email,pass).getPersonId());


    }
    @Test
    public void testFindpersonById() throws JsonParseException,
            JsonMappingException, IOException {

        assertThrows(PersonNotFoundException.class,()->personService.findPerson(78));

    }
    @Test
    public void testFindpersonByemail() throws JsonParseException,
            JsonMappingException, IOException {

        assertThrows(PersonNotFoundException.class,()->personService.findPerson("hfjdshkjfdh","jfhskjqhfsk"));

    }

    @Test
    public void size() throws JsonParseException,
            JsonMappingException, IOException {


        assertThrows(PersonNotFoundException.class,()->personService.findPerson("hfjdshkjfdh","jfhskjqhfsk"));

    }
}
