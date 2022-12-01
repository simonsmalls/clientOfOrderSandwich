package be.abis.exjunit;


import be.abis.exjunit.exception.NoAbisEmplyException;
import be.abis.exjunit.exception.SalaryTooLowException;
import be.abis.exjunit.model.Address;
import be.abis.exjunit.model.Company;
import be.abis.exjunit.model.Person;
import be.abis.exjunit.service.AbisPaymentService;
import be.abis.exjunit.util.SecurityChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class PaymentServiceTest {
    @Mock
    Person mockPerson;
    @Mock
    SecurityChecker checker;
    @Mock
    Company company;
    @Mock
    Address address;
    AbisPaymentService abisPaymentService;

    @BeforeEach
    void setUp(){
        abisPaymentService=new AbisPaymentService();


    }

    @Test
    public void doIt(){

    }




    @Spy
    SecurityChecker securityChecker=new SecurityChecker();
    @Test

    public void notEmployeeOfAbis()  {

        //when(mockPerson.getFirstName()).thenReturn("sim");


        when(company.getName()).thenReturn("sa");
        when(mockPerson.getCompany()).thenReturn(company);
        AbisPaymentService abisPaymentService=new AbisPaymentService();
        abisPaymentService.setSecurityChecker(securityChecker);
        //securityChecker.isAbisEmployee(mockPerson);

        //Mockito.verify(securityChecker).isAbisEmployee(mockPerson);



        assertThrows(NoAbisEmplyException.class,()->abisPaymentService.paySalary(mockPerson));
    }



}