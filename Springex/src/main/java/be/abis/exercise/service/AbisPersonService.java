package be.abis.exercise.service;

import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@Primary
@Profile("production")
public class AbisPersonService implements PersonService{
    @Autowired
    PersonRepository fps;

    @Override
    public ArrayList<Person> getAllPersons() {
        return fps.getAllPersons();
    }

    @Override
    public Person findPerson(int id) {
        return fps.findPerson(id);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        return fps.findPerson(emailAddress,passWord);
    }

    @Override
    public boolean addPerson(Person p) throws IOException {
        fps.addPerson(p);

        return false;
    }

    @Override
    public void deletePerson(int id) {
        fps.deletePerson(id);

    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {
        fps.changePassword(p,newPswd);

    }
}
