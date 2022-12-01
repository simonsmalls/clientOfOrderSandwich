package be.abis.exercise;


import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)

class CompanyTest {
    Company c1;
    Company c2;
    Company c3;


    @BeforeEach
    void setUp(){
        c1=new Company("abis");
        c2=new Company("abis");
        c3=new Company("smalls");
    }

    @Test
    void equalCorrect(){
        assertEquals(c1,c2);
    }

    @Mock
    Address mockAddress1;
    @Mock
    Address mockAddress2;

    @Test
    void equalCorrectAddres(){

        c1.setAddress(mockAddress1);
        c2.setAddress(mockAddress1);
        assertEquals(c1,c2);
    }

    @Test
    void equalNotCorrectAddres(){

        c1.setAddress(mockAddress1);
        c2.setAddress(mockAddress2);
        assertNotEquals(c1,c2);

    }

    @Test
    void equalfalse(){
        assertFalse(c1.equals(c3));
    }

}