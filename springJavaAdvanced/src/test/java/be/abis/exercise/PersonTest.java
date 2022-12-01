package be.abis.exercise;

import be.abis.exercise.exception.EmailException;
import be.abis.exercise.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person p1;
    Person p2;
    Person p3;


    @BeforeEach
    void setUp(){
        p1=new Person("sim","haas");
        p2=new Person("jana","heit");
        p3=new Person("sim","haas");
    }

    @Test
    void equalsCorrect(){
        assertEquals(p1,p3);
    }

    @Test
    void equalsInCorrect(){
        assertNotEquals(p1,p2);
    }
    @Test
    @Tag("age")
    void ageCorrect(){
        p1.setBirthDate(LocalDate.now().minusDays(364).minusYears(24));
        assertEquals(24,p1.calculateAge());
    }
    @Test
    @Tag("age")
    @DisplayName("random")
    void age25Correct(){
        p1.setBirthDate(LocalDate.now().minusDays(365).minusYears(24));
        assertEquals(25,p1.calculateAge());
    }

    @Test
    void emailCorrect(){
        try {
            p1.setEmail("test@bla.com");
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void emailIncorrect(){
        assertThrows(EmailException.class   ,()->p1.setEmail("nla"));
    }



}