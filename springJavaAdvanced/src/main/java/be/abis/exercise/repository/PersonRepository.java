package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;

import java.nio.file.Path;
import java.util.List;

public interface PersonRepository {

     void writeAllPersonsToFile();
     public List<Person> FileToPersonList(String path);
     public void writePersonToFile(Path path, Person person);
     public Person readIn(String[] values);
     public void addPerson(Person p);
     public Person findPerson(int id) throws PersonNotFoundException;
     public Person findPerson(String email,String pass) throws PersonNotFoundException;
     public List<Person> removePersonsWithoutCompany();
     public List<Person> getPersonList();

}
