package be.abis.clientrest.service;


import be.abis.clientrest.model.Persons;
import be.abis.clientrest.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RateImpl implements RateService {
    @Autowired
    private RestTemplate rt;
   String baseUrl="https://api.exchangerate.host/convert";
    @Override
    public double getExchngeRate(String from, String to) {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("from", from).queryParam("to",to);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("accept", MediaType.APPLICATION_XML_VALUE);
        HttpEntity<Rate> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Rate> g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, Rate.class);

        Rate rate= g.getBody();
        return rate.getResult();
    }
}
