package be.abis.clientrest.service;

import be.abis.clientrest.exception.ApiException;
import be.abis.clientrest.exception.ValidationException;
import be.abis.clientrest.exception.PersonAlreadyExistsException;
import be.abis.clientrest.exception.PersonNotFoundException;
import be.abis.clientrest.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();
    Person findPerson(int id) throws JsonProcessingException, PersonNotFoundException;
    Person findPerson(String emailAddress, String passWord) throws JsonProcessingException, PersonNotFoundException;
    void addPerson(Person p) throws IOException, PersonAlreadyExistsException, ValidationException;
    public void deletePerson(int id) throws JsonProcessingException, PersonNotFoundException;
    void changePassword(Person p, String newPswd,String apikey) throws IOException, ValidationException, ApiException;
    List<Person> findPersonsByCompanyName(String name);
    
}
