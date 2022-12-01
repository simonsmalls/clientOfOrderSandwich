package be.abis.clientrest.cliet;

import be.abis.clientrest.model.Person;
import be.abis.clientrest.service.PersonService;
import be.abis.clientrest.service.RateService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RateTest {

    @Autowired
    RateService rateService;
    String baseUrl="http://localhost:8080/exercise/personapi/persons";
    @Test
    public void testFindGuestById(){
        double a= rateService.getExchngeRate("EUR","JPY");
        System.out.println(a);
    }
}
