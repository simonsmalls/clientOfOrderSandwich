package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.exception.SessionNotFoundException;
import be.abis.cllientsandwich.model.OrderToday;
import be.abis.cllientsandwich.model.Person;
import be.abis.cllientsandwich.model.Session;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface SessionService {

    String checkAllOrdered(OrderToday orderToday, Session session);
    // repository methods

    List<Session> getSessions();
    Session findMostRecentSession(String sessionName) throws SessionNotFoundException, JsonProcessingException;
    void addSession(Session session);
    List<Person> getAllPersonsFromSession(Session session) throws SessionNotFoundException, JsonProcessingException;


}
