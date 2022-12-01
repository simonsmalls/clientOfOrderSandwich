package be.abis.exercise.service;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
@Service
@Profile("test")
public class SomePersonService implements PersonService{
    @Override
    public ArrayList<Person> getAllPersons() {
        return null;
    }

    @Override
    public Person findPerson(int id) {

        return new Person(8,"sim","haas",25,"test@gmail.com","test555","dutch",new Company());
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        return new Person(8,"sim","haas",25,"test@gmail.com","test555","dutch",new Company());
    }

    @Override
    public boolean addPerson(Person p) throws IOException {
        return false;
    }

    @Override
    public void deletePerson(int id) {

    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {

    }
}
