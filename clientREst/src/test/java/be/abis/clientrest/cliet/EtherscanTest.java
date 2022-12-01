package be.abis.clientrest.cliet;

import be.abis.clientrest.model.Contract;
import be.abis.clientrest.model.Rate;
import be.abis.clientrest.service.RateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
public class EtherscanTest {
    @Autowired
    private RestTemplate rt;

    String baseUrl="https://api.etherscan.io/api";
    @Test
    public void testFindGuestById(){

        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("module", "contract")
                .queryParam("action", "getabi")
                .queryParam("address", "0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413")
                .queryParam("apikey","YMKV4N7BJ2A5GG9KU3B7FN4D16KAI9MQ8E");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Contract> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Contract> g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, Contract.class);

        Contract contract= g.getBody();
        System.out.println(  contract.getResult());
    }
}
