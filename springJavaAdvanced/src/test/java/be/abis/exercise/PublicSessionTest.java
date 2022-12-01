package be.abis.exercise;

import be.abis.exercise.model.*;
import be.abis.exercise.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class PublicSessionTest {

    @Mock
    Person p1;
    @Mock
    CourseParticipant cp1;
    @Mock
    CourseParticipant cp2;
    @Mock
    CourseParticipant cp3;
    @Mock
    CourseParticipant cp4;
    @Mock
    Company company;


    PublicSession publicSession;

    @BeforeEach
    void setUp(){
        publicSession=new PublicSession(Course.INTERNET_ENABLING, LocalDate.now(),company,p1);

    }

    @Test
    void addEnrollment(){
        publicSession.addEnrolment(cp1);
        publicSession.addEnrolment(cp1);
        assertEquals(publicSession.getEnrolments().size(),1);
    }

    @Test
    void addEnrollments(){
        CourseParticipant[] array={cp1,cp2};
        publicSession.addEnrolment(array);
        assertEquals(publicSession.getEnrolments().size(),2);
    }

    @Test
    void removeEnrollments(){
        CourseParticipant[] array={cp1,cp2};
        publicSession.addEnrolment(array);
        publicSession.removeEnrolment(cp1);
        assertEquals(publicSession.getEnrolments().size(),1);
    }
    @Test
    void removeNonExistingEnrollments(){
        CourseParticipant[] array={cp1,cp2};
        publicSession.addEnrolment(array);
        publicSession.removeEnrolment(cp3);
        assertEquals(publicSession.getEnrolments().size(),2);
    }
    @Test
    void revenue(){
        publicSession.addEnrolment(cp1);
        publicSession.addEnrolment(cp2);
        System.out.println(publicSession.revenue());
        assertEquals(publicSession.revenue(),3160*2);
    }

@Test
    void tosString(){
        Locale locale=new Locale("en");
        publicSession.toStringInt(locale);
}

}