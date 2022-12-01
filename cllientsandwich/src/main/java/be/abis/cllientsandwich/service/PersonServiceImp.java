package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.error.ApiError;
import be.abis.cllientsandwich.exception.*;
import be.abis.cllientsandwich.model.Name;
import be.abis.cllientsandwich.model.OrderToday;
import be.abis.cllientsandwich.model.Person;
import be.abis.cllientsandwich.model.SandwichOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonServiceImp implements PersonService{
    @Autowired
    private RestTemplate rt;

    String baseUrl="http://localhost:8080/ordersandwich/api/persons";


    @Override
    public List<Integer> checkMyOrderToday(Person person, OrderToday orderToday) throws NullInputException {
        return null;
    }

    @Override
    public List<Person> getPersonList() {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

        HttpHeaders headers = new HttpHeaders();


        HttpEntity<List<Person>> requestEntity = new HttpEntity<>(headers);
        ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, Person[].class);

        Person[] list = (Person[]) g.getBody();




        return Arrays.asList(list);
    }

    @Override
    public void addPerson(Person person) throws JsonProcessingException, PersonExistsException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/add");

            HttpHeaders headers = new HttpHeaders();



            HttpEntity<Person> requestEntity = new HttpEntity<>(person,headers);
            rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Person.class);


        } catch (HttpStatusCodeException e) {
            if (HttpStatus.CONFLICT == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new PersonExistsException("person not Found");

            } else {
                System.out.println("some other error occurred");
            }
        }
    }

    @Override
    public void removePerson(Person person) throws PersonNotFoundException, JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

            HttpHeaders headers = new HttpHeaders();



            HttpEntity<Person> requestEntity = new HttpEntity<>(person,headers);
             rt.exchange(uriBuilder.toUriString(), HttpMethod.DELETE,requestEntity, Person.class);


        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new PersonNotFoundException("person not Found");

            } else {
                System.out.println("some other error occurred");
            }
        }
    }

    @Override
    public Person findPerson(String firstName,String lastName) throws PersonNotFoundException, JsonProcessingException {


        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/name");

            HttpHeaders headers = new HttpHeaders();
            Name name = new Name();
            name.setFirstName(firstName);
            name.setLastName(lastName);


            HttpEntity<Name> requestEntity = new HttpEntity<>(name,headers);
            ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Person.class);
            Person p= (Person) g.getBody();
            return p;

        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new PersonNotFoundException("person not found");

            } else {
                System.out.println("some other error occurred");
            }
        }


        return null;
    }

    @Override
    public Person findPerson(int id) throws PersonNotFoundException, JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/" +id);

            HttpHeaders headers = new HttpHeaders();



            HttpEntity<Name> requestEntity = new HttpEntity<>(headers);
            ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Person.class);
            Person p= (Person) g.getBody();
            return p;

        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new PersonNotFoundException("person not found");

            } else {
                System.out.println("some other error occurred");
            }
        }


        return null;
    }
}
