package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.exception.*;
import be.abis.cllientsandwich.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalTime;
import java.util.List;

public interface OrderTodayService {
    void orderSandwich(SandwichOrderModel model) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException, JsonProcessingException;
    void noOrder(Person person) throws TooManySandwichesException, TooLateException, NullInputException, JsonProcessingException, PersonNotFoundException;
    void removeOrder(SandwichOrder order) throws TooLateException, NullInputException, JsonProcessingException;
    double totalPrice() throws NullInputException;

    void sendOrder(int id) throws NullInputException;

    void toFile(String writing, boolean bool);

    public void setClosingTime(LocalTime closingTime);
    public OrderToday getOrderToday();
    public void setOrderToday(int id);
    public void setOrderTomorrow(int id);

    List<SandwichOrder> checkMyOrderToday(Person person) throws NullInputException, JsonProcessingException;
    String checkAllOrderedString() throws JsonProcessingException;
    List<Person> checkAllOrderedPersons( ) throws JsonProcessingException;

}
