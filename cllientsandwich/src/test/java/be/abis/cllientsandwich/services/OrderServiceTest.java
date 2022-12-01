package be.abis.cllientsandwich.services;

import be.abis.cllientsandwich.exception.*;
import be.abis.cllientsandwich.model.*;
import be.abis.cllientsandwich.service.OrderTodayService;
import be.abis.cllientsandwich.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {
    @Autowired
    OrderTodayService service;

    @Autowired
    PersonService personService;

    Person p = new Person("haas","jkdfs");
    Person p2 = new Person("jana","kjsdfq");
    Person p3= new Person("esben","jklsdfj");

    @BeforeEach
    void setup() throws PersonNotFoundException, JsonProcessingException {
        Shop shop=new Shop("Vleugels");
        shop.setId(1);
        p = personService.findPerson(2);
        OrderToday o=new OrderToday(shop);
        o.setClosingTime(LocalTime.MAX);
        service.setOrderToday(1);


    }


    @Test
    void sendOrder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, JsonProcessingException, NullInputException {

        SandwichOrderModel model=new SandwichOrderModel(1,false,true,true, "",p);


        service.orderSandwich(model);
        service.orderSandwich(model);
    // shop toevoegen
        service.sendOrder(1);

    }
    @Test
    void Order() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);

        assertEquals("Jietse Molenaers",service.getOrderToday().getOrder().get(0).getPerson().getName());

    }
    @Test
    void NoOrder() throws TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException, PersonNotFoundException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.noOrder(p);

        assertEquals("Jietse Molenaers",service.getOrderToday().getOrder().get(0).getPerson().getName());

    }

    @Test
    void remove() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);
        service.removeOrder(service.getOrderToday().getOrder().get(0));
        //System.out.println(        service.getOrderToday().getOrder().get(0).getId());

        assertEquals(1,service.getOrderToday().getOrder().size());

    }

    @Test
    void removenull() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);
        service.removeOrder(null);
        //System.out.println(        service.getOrderToday().getOrder().get(0).getId());

        assertEquals(2,service.getOrderToday().getOrder().size());

    }
    @Test
    void removerand() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);

        SandwichOrder order= service.getOrderToday().getOrder().get(0);
        order.setId(78);
        service.removeOrder(order);
        //System.out.println(        service.getOrderToday().getOrder().get(0).getId());

        assertEquals(2,service.getOrderToday().getOrder().size());

    }

    @Test
    void NoOrderNull() throws TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException, PersonNotFoundException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.noOrder(null);

        assertEquals(0,service.getOrderToday().getOrder().size());

    }

    @Test
    void OrderNull() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(null);

        assertEquals(0,service.getOrderToday().getOrder().size());

    }
    @Test
    void OrderModelNull() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);



        assertThrows(SandwichTypeNotFoundException.class,()->service.orderSandwich(new SandwichOrderModel()));

    }
    @Test
    void price() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);

        assertEquals(3.56*2,service.totalPrice());

    }

    @Test
    void OrderTomorrow() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {
        Shop shop=new Shop("Vleugels");
        service.setOrderTomorrow(2);
        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);



    }
    @Test
    void checkOrder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);
        System.out.println(service.checkMyOrderToday(p));

        assertEquals(2,service.checkMyOrderToday(p).size());

    }
    @Test
    void checkOrdernull() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);
        Person p=null;
        service.checkMyOrderToday(p);
        System.out.println(  service.checkMyOrderToday(p));
        //assertTrue(false);

    }
    @Test
    void checkOrderNewPerson() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {

        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        service.orderSandwich(model);
        Person pe=new Person("hello","kjsdfkl");
        System.out.println(service.checkMyOrderToday(pe));

    }

    @Test
    void checkAllOrderedString() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {


        System.out.println(service.checkAllOrderedString());

    }
    @Test
    void checkAllOrderedPersons() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, JsonProcessingException {
        SandwichOrderModel model=new SandwichOrderModel(1,true,true,true, "",p);

        service.orderSandwich(model);
        System.out.println(service.checkAllOrderedPersons());

    }






}
