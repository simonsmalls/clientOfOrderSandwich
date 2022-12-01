package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.error.ApiError;
import be.abis.cllientsandwich.exception.PersonNotFoundException;
import be.abis.cllientsandwich.exception.SessionNotFoundException;
import be.abis.cllientsandwich.model.*;
import be.abis.cllientsandwich.exception.SessionNotFoundException;
import be.abis.cllientsandwich.model.OrderToday;
import be.abis.cllientsandwich.model.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class SessionServiceImp implements SessionService{

    @Autowired
    private RestTemplate rt;

    String baseUrl="http://localhost:8080/ordersandwich/api/session";

    @Override
    public String checkAllOrdered(OrderToday orderToday, Session session) {
        return null;
    }

    @Override
    public List<Session> getSessions() {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

        HttpHeaders headers = new HttpHeaders();


        HttpEntity<List<Session>> requestEntity = new HttpEntity<>(headers);
        ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, Session[].class);

        Session[] list = (Session[]) g.getBody();




        return Arrays.asList(list);
    }

    @Override
    public Session findMostRecentSession(String sessionName) throws SessionNotFoundException, JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/name");

            HttpHeaders headers = new HttpHeaders();
            Name name = new Name();
            name.setName(sessionName);


            HttpEntity<Name> requestEntity = new HttpEntity<>(name,headers);
            ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Session.class);
            Session session= (Session) g.getBody();
            return session;

        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new SessionNotFoundException("session not found");

            } else {
                System.out.println("some other error occurred");
            }
        }
        return null;
    }

    @Override
    public void addSession(Session session) {

    }

    @Override
    public List<Person> getAllPersonsFromSession(Session session) throws SessionNotFoundException, JsonProcessingException {

        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/persons");

            HttpHeaders headers = new HttpHeaders();



            HttpEntity<Name> requestEntity = new HttpEntity<>(headers);
            ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, Person[].class);
            Person[] persons= (Person[]) g.getBody();
            return Arrays.asList(persons);

        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new SessionNotFoundException("session not found");

            } else {
                System.out.println("some other error occurred");
            }
        }
        return null;

    }
}
