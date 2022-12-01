package be.abis.exercise;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import be.abis.exercise.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressTest {
    @Autowired
    AddressService addressService;


    @Test
    public void zipCodeCorrectBE(){
        Address address=new Address("loui","9","3000","leuv","belg","BE");
        assertDoesNotThrow(()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodeletterBE(){
        Address address=new Address("loui","9","l205","leuv","belg","BE");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCode0startBE(){
        Address address=new Address("loui","9","0300","leuv","belg","BE");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodetoolongBE(){
        Address address=new Address("loui","9","30000","leuv","belg","BE");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodetooshortBE(){
        Address address=new Address("loui","9","300","leuv","belg","BE");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodeNullBE(){
        Address address=new Address("loui","9","","leuv","belg","BE");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }

    @Test
    public void countryCodeNull(){
        Address address=new Address("loui","9","","leuv","belg","");
        assertDoesNotThrow(()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }

    @Test
    public void zipCodeCorrectNL(){
        Address address=new Address("loui","9","3000ZS","leuv","belg","NL");
        assertDoesNotThrow(()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodeletterNL(){
        Address address=new Address("loui","9","7l0000","leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodeonlynumberNL(){
        Address address=new Address("loui","9","300000","leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodesmallletterNL(){
        Address address=new Address("loui","9","3000ss","leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCode0startNL(){
        Address address=new Address("loui","9","0700NL","leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodetoolongNL(){
        Address address=new Address("loui","9","30000NL","leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodetooshortNL(){
        Address address=new Address("loui","9","300NL","leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodeNullNL(){
        Address address=new Address("loui","9","","leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }
    @Test
    public void zipCodeNull0NL(){
        Address address=new Address("loui","9",null,"leuv","belg","NL");
        assertThrows(ZipCodeNotCorrectException.class,()->addressService.checkZipCode(address.getCountryCode(), address.getZipCode()));
    }


}