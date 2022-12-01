package be.abis.exjunit;


import be.abis.exjunit.exception.PersonShouldBeAdultException;
import be.abis.exjunit.exception.SalaryTooLowException;
import be.abis.exjunit.model.Address;
import be.abis.exjunit.model.Company;
import be.abis.exjunit.model.Person;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.text.WordUtils;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

class PersonTest {
    Person p1;
    Person p2;
    @BeforeEach
    void setUp(){
        p1= new Person(1,"Sim","haas", LocalDate.of(1980,2,01));
        p2= new Person(1,"Sim","haas", LocalDate.of(2010,2,01));
    }




    @Test
    @Order(1)
    void person1ShouldBe42YearsOld(){


        try {
            //assertTrue(p1.calculateAge()==42);
            assertThat(p1.calculateAge(),equalTo(42));
        } catch (PersonShouldBeAdultException e) {
            System.out.println(e.getMessage());
            assertTrue(false   );
        }


    }

    @Test

    void toStringSentenceStartWithPerson() {

        String test="Person";
        assertThat(p1.toString().substring(0,test.length()),equalTo(test));
    }
    @Test
    @Order(2)
    void personShouldBeAdult(){

       assertThrows(PersonShouldBeAdultException.class,()->p2.calculateAge());

    }

    Address address=new Address("lou","9","3440","zout","Belg","BE");

    Company company=new Company("abis",address);

    @Mock
    private Company mockCompany;
    @Mock
    private Address mockAddress;

    @Test
    public void belg(){
        when(mockAddress.getCountryCode()).thenReturn("BE");
        when(mockCompany.getAddress()).thenReturn(mockAddress);
        //when(mockAddress.getCountryCode()).thenReturn("BE");
        //when(mockAddress.getCountryCode()).thenReturn("BE");
        assertEquals("BE",mockCompany.getAddress().getCountryCode());
    }

    @Test
    public void netSalaryBelg(){
        when(mockCompany.calculateTaxToPay()).thenReturn(51.0);


        Person p3= new Person(1,"sim","haas", LocalDate.of(1997,9,3),mockCompany);
        p3.setGrossSalary(1000000);

        try {
            assertEquals(490000/12*3+1,p3.calculateNet()*3);
        } catch (SalaryTooLowException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void netLow(){
        when(mockCompany.calculateTaxToPay()).thenReturn(51.0);


        Person p3= new Person(1,"sim","haas", LocalDate.of(1997,9,3),mockCompany);
        p3.setGrossSalary(1000);

        assertThrows(SalaryTooLowException.class,()->p3.calculateNet());
        verify(mockCompany).calculateTaxToPay();
    }

    @Test
    public  void cap(){
        String a=" test ";
        System.out.println(capitalize(a));
        System.out.println(a);
        assertTrue(true);

    }



}