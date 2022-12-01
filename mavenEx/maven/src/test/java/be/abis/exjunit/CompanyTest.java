package be.abis.exjunit;


import be.abis.exjunit.model.Address;
import be.abis.exjunit.model.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CompanyTest {

    @Test
    public void taxIs51(){
        Address address=new Address("lou","9","3440","zout","Belg","BE");
        Company company=new Company("abis",address);

        assertEquals(company.calculateTaxToPay(),51.0);
    }



}