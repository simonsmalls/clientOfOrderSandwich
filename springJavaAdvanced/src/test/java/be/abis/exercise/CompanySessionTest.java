package be.abis.exercise;

import be.abis.exercise.exception.InvoiceException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.CompanySession;
import be.abis.exercise.model.CourseInterface;
import be.abis.exercise.model.Instructor;
import be.abis.exercise.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CompanySessionTest {
    CompanySession companySession;
    @Autowired
    SessionRepository sessionRepository;
    @Mock
    CourseInterface course;
    @Mock
    Company company;
    @Mock
    Instructor instructor;
    @BeforeEach
    void setUp(){
        companySession=new CompanySession(course, LocalDate.now(),company,instructor,company,8);
    }

    @Test
    void invoiceCorrect(){
        when(course.getDays()).thenReturn(3);
        when(course.getDailyPrice()).thenReturn(50.0);
        try {
            System.out.println(companySession.invoice());
        } catch (InvoiceException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void invoiceTooLow(){
        when(course.getDays()).thenReturn(3);
        when(course.getDailyPrice()).thenReturn(5.0);
        assertThrows(InvoiceException.class,companySession::invoice);


    }
    @Test
    void invoiceTooHigh(){

        when(course.getDays()).thenReturn(3);
        when(course.getDailyPrice()).thenReturn(50000.0);
        assertThrows(InvoiceException.class,companySession::invoice);

    }

}