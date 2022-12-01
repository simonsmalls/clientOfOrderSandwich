package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.exception.*;
import be.abis.cllientsandwich.model.OrderToday;
import be.abis.cllientsandwich.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PersonService {
    //public void removeMyOrder(Person person, OrderToday orderToday) throws TooLateException, NullInputException;
    public List<Integer> checkMyOrderToday(Person person, OrderToday orderToday) throws NullInputException;
    List<Person> getPersonList();

    void addPerson(Person person) throws JsonProcessingException, PersonExistsException;

    void removePerson(Person person) throws PersonNotFoundException, JsonProcessingException;

    Person findPerson(String firstName,String lastName) throws PersonNotFoundException, JsonProcessingException;
    Person findPerson(int id) throws PersonNotFoundException, JsonProcessingException;

    }
