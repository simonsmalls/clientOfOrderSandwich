package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.error.ApiError;
import be.abis.cllientsandwich.exception.SandwichTypeNotFoundException;
import be.abis.cllientsandwich.model.FinanceModel;
import be.abis.cllientsandwich.model.SandwichOrderModel;
import be.abis.cllientsandwich.model.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Month;

@Service
public class FinancialManagerImp implements FinancialManager{


    @Autowired
    private RestTemplate rt;

    String baseUrl="http://localhost:8080/ordersandwich/api/finance";
    @Override
    public double getMonthlyPrice(Shop shop, Month month, int year) throws JsonProcessingException, SandwichTypeNotFoundException {

        FinanceModel model = new FinanceModel();
        model.setShop(shop);
        model.setMonth(month);
        model.setYear(year);


        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/monthlyprice/shop");

        HttpHeaders headers = new HttpHeaders();


        HttpEntity<FinanceModel> requestEntity = new HttpEntity<>(model,headers);
        ResponseEntity g =  rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, double.class);
        return (double) g.getBody();


    }

    @Override
    public int getAmountOfSandwichesOrdered(Shop shop, Month month, int year) {
        FinanceModel model = new FinanceModel();
        model.setShop(shop);
        model.setMonth(month);
        model.setYear(year);
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/amountofsandwiches/shop");

        HttpHeaders headers = new HttpHeaders();


        HttpEntity<FinanceModel> requestEntity = new HttpEntity<>(model,headers);
        ResponseEntity g =  rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Integer.class);
        return (Integer) g.getBody();
    }

    @Override
    public double averagePriceSandwich(Shop shop, Month month, int year) {
        FinanceModel model = new FinanceModel();
        model.setShop(shop);
        model.setMonth(month);
        model.setYear(year);
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/average");

        HttpHeaders headers = new HttpHeaders();


        HttpEntity<FinanceModel> requestEntity = new HttpEntity<>(model,headers);
        ResponseEntity g =  rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, double.class);
        return (double) g.getBody();
    }
}
