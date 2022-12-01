package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.error.ApiError;
import be.abis.cllientsandwich.exception.*;
import be.abis.cllientsandwich.model.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderTodayServiceImp implements OrderTodayService{


    OrderToday orderToday;

    @Autowired
    private RestTemplate rt;

    String baseUrl="http://localhost:8080/ordersandwich/api/order";


    @Override
    public void orderSandwich(SandwichOrderModel model) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException, JsonProcessingException {

        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

            HttpHeaders headers = new HttpHeaders();


            HttpEntity<SandwichOrderModel> requestEntity = new HttpEntity<>(model,headers);
            rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, SandwichOrderModel.class);


        } catch (HttpStatusCodeException e) {

            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new SandwichTypeNotFoundException("sandwich not found");

            } else {
                String serr = e.getResponseBodyAsString();
                System.out.println(serr);
            }
        }




    }

    @Override
    public void noOrder(Person person) throws TooManySandwichesException, TooLateException, NullInputException, JsonProcessingException, PersonNotFoundException {

        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/none");

            HttpHeaders headers = new HttpHeaders();




            HttpEntity<Person> requestEntity = new HttpEntity<>(person,headers);
            rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Person.class);


        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
               // System.out.println(ae.getDescription());
                throw new PersonNotFoundException("Person not found");

            } else {
                String serr = e.getResponseBodyAsString();
                System.out.println(serr);
            }
        }

    }

    @Override
    public void removeOrder(SandwichOrder order) throws TooLateException, NullInputException, JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

            HttpHeaders headers = new HttpHeaders();


            HttpEntity<SandwichOrder> requestEntity = new HttpEntity<>(order,headers);
            rt.exchange(uriBuilder.toUriString(), HttpMethod.DELETE,requestEntity, SandwichOrder.class);



        } catch (HttpStatusCodeException e) {

            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());


            } else {
                String serr = e.getResponseBodyAsString();
                System.out.println(serr);
            }
        }


    }

    @Override
    public double totalPrice() throws NullInputException {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/price");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);
        ResponseEntity g =rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, double.class);
        return (double) g.getBody();
    }

    @Override
    public void sendOrder(int id) throws NullInputException {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/send/shop/"+id);

        HttpHeaders headers = new HttpHeaders();




        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);
        ResponseEntity g =rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Person.class);



    }

    @Override
    public void toFile(String writing, boolean bool) {

    }

    @Override
    public void setClosingTime(LocalTime closingTime) {

    }

    @Override
    public OrderToday getOrderToday() {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

        HttpHeaders headers = new HttpHeaders();


        HttpEntity<OrderToday> requestEntity = new HttpEntity<>(headers);
        ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, OrderToday.class);






       return (OrderToday) g.getBody();
    }

    @Override
    public void setOrderToday(int id) {

        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/new/today/"+id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Shop> requestEntity = new HttpEntity<>(headers);
        ResponseEntity g=rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Shop.class);


    }

    @Override
    public void setOrderTomorrow(int id) {

        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/new/tomorrow/"+id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<Shop> requestEntity = new HttpEntity<>(headers);
        ResponseEntity g=rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Shop.class);

    }

    @Override
    public List<SandwichOrder> checkMyOrderToday(Person person) throws JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/check/person");

            HttpHeaders headers = new HttpHeaders();


            HttpEntity<Person> requestEntity = new HttpEntity<>(person,headers);

            ResponseEntity g=rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, SandwichOrder[].class);

            SandwichOrder[] list = (SandwichOrder[]) g.getBody();

            return Arrays.asList(list);



        } catch (HttpStatusCodeException e) {


            if (HttpStatus.CONFLICT== e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());


            } else {
                String serr = e.getResponseBodyAsString();
                System.out.println(serr);
            }
        }

        return null;
    }

    @Override
    public String checkAllOrderedString() throws JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/check/all");

            HttpHeaders headers = new HttpHeaders();


            HttpEntity<Session> requestEntity = new HttpEntity<>(headers);
            ResponseEntity g=rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, String.class);


            return (String) g.getBody();



        } catch (HttpStatusCodeException e) {

            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());


            } else {
                String serr = e.getResponseBodyAsString();
                System.out.println(serr);
            }
        }

        return null;
    }

    @Override
    public List<Person> checkAllOrderedPersons() throws JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+ "/check/allpersons");

            HttpHeaders headers = new HttpHeaders();


            HttpEntity<Session> requestEntity = new HttpEntity<>(headers);
            ResponseEntity g=rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Person[].class);
            Person[] list = (Person[]) g.getBody();

            return Arrays.asList(list);



        } catch (HttpStatusCodeException e) {

            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());


            } else {
                String serr = e.getResponseBodyAsString();
                System.out.println(serr);
            }
        }

        return null;
    }
}
