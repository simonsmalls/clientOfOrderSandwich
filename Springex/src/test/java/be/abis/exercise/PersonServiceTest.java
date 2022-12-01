package be.abis.exercise;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonServiceTest {
    @Autowired
    PersonService ps;

    @Test
    void findId() {
        System.out.println("does");
        ps.findPerson(1);
        assertEquals(ps.findPerson(1).getFirstName(),"John");

    }

    @Test
    void getall() {
        assertEquals(ps.getAllPersons().size(),4);

    }

    @Test
    @Order(2)
    void findadd() {
        Address address=new Address("blz","leuv","3000",9);
        Company company=new Company("abis","011785566","587",address);
        Person p=new Person(5,"si","haas",25,"sim@gmil.com","e4557","dutch",company);
        try {
            System.out.println(ps.addPerson(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ps.findPerson(5));
    }

    @Test
    void newpass(){
        try {
            ps.changePassword(ps.findPerson(1),"newpassmyman");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(1)
    void delete(){
        ps.deletePerson(5);
    }



}
