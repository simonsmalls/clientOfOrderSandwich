package be.abis.exercise.service;

import be.abis.exercise.model.Person;

import java.io.IOException;
import java.util.ArrayList;

public interface PersonService {

    ArrayList<Person> getAllPersons();
    Person findPerson(int id);
    Person findPerson(String emailAddress, String passWord);
    boolean addPerson(Person p) throws IOException;
    public void deletePerson(int id);
    void changePassword(Person p, String newPswd) throws IOException;
}
