package be.abis.clientrest.service;

import be.abis.clientrest.error.ApiError;
import be.abis.clientrest.error.ValidationError;
import be.abis.clientrest.exception.ApiException;
import be.abis.clientrest.exception.ValidationException;
import be.abis.clientrest.exception.PersonAlreadyExistsException;
import be.abis.clientrest.exception.PersonNotFoundException;
import be.abis.clientrest.model.EmailPass;
import be.abis.clientrest.model.Person;
import be.abis.clientrest.model.Persons;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class AbisPersonService implements PersonService {




	@Autowired
	private RestTemplate rt;

	String baseUrl="http://localhost:8080/exercise/personapi/persons";

	HttpHeaders createHeaders(String username, String password){
		return new HttpHeaders() {{
			String auth = username + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64(
					auth.getBytes(Charset.forName("US-ASCII")) );
			String authHeader = "Basic " + new String( encodedAuth );
			set( "Authorization", authHeader );
		}};
	}

	@PostConstruct
	public void init() {
		rt.getInterceptors()
				.add(new BasicAuthenticationInterceptor("user2", "pwd2"));

	}
	
	@Override
	public List<Person> getAllPersons() {
		UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);
		String auth = "user1" + ":" + "pwd1";
		byte[] encodedAuth = Base64.encodeBase64(
				auth.getBytes(Charset.forName("US-ASCII")) );
		String authHeader = "Basic " + new String( encodedAuth );

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authHeader);

		HttpEntity<Persons> requestEntity = new HttpEntity<>(headers);
		ResponseEntity g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, Person[].class);

		Person[] list = (Person[]) g.getBody();


		return Arrays.asList(list);
	}

	@Override
	public Person findPerson(int id) throws JsonProcessingException, PersonNotFoundException {
		ResponseEntity<? extends Object> re=null;
		Person person=null;

		try {
			re = rt.getForEntity(baseUrl+ "/"+id, Person.class);
			person = (Person) re.getBody();




		} catch (HttpStatusCodeException e) {
			if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
				String serr = e.getResponseBodyAsString();
				System.out.println(serr);
				ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
				System.out.println(ae.getDescription());
				throw new PersonNotFoundException("person not found");

			} else {
				System.out.println("some other error occurred");
			}
		}

		return person;
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) throws JsonProcessingException, PersonNotFoundException {



		ResponseEntity<? extends Object> re=null;
		Person person=null;

		try {
			EmailPass emailPass=new EmailPass(emailAddress,passWord);

			UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/login");
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
			requestHeaders.set("Authorization", "User1"+"pwd1");
			HttpEntity<EmailPass> requestEntity = new HttpEntity<>(emailPass,createHeaders("user1","pwd1"));
			ResponseEntity<Person> g = rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Person.class);
			person = g.getBody();
			System.out.println(g.getHeaders().get("api-key"));

		} catch (HttpStatusCodeException e) {
			if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
				String serr = e.getResponseBodyAsString();
				System.out.println(serr);
				ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
				System.out.println(ae.getDescription());
				throw new PersonNotFoundException("person not found");

			} else {
				System.out.println("some other error occurred");
			}
		}
		return person;

	}

	@Override
	public void addPerson(Person p) throws IOException, PersonAlreadyExistsException, ValidationException {



		ResponseEntity<? extends Object> re=null;
		Person person=null;

		try {
			re = rt.postForEntity(baseUrl, p, Person.class);
			person = (Person) re.getBody();
			System.out.println("hello");



		} catch (HttpStatusCodeException e) {

			if (HttpStatus.CONFLICT == e.getStatusCode()) {
				String serr = e.getResponseBodyAsString();
				System.out.println(serr);
				ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
				System.out.println(ae.getDescription());
				throw new PersonAlreadyExistsException("person not found");

			} else if((HttpStatus.BAD_REQUEST == e.getStatusCode()))  {
				String serr = e.getResponseBodyAsString();
				System.out.println(serr);
				ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
				List<ValidationError> ve = ae.getInvalidParams();
				if (ve.size()!=0){
					//System.out.println("validation errors:");
					//ve.stream().map(v->v.getReason()).forEach(System.out::println);
					System.out.println(ve.toString());
					throw new ValidationException(ve.toString());
				}





			}
		}

	}

	@Override
	public void deletePerson(int id) throws JsonProcessingException, PersonNotFoundException {


		try {
			rt.delete(baseUrl+ "/"+id, Person.class);



		} catch (HttpStatusCodeException e) {
			if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
				String serr = e.getResponseBodyAsString();
				System.out.println(serr);
				ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
				System.out.println(ae.getDescription());
				throw new PersonNotFoundException("person not found");

			} else {
				System.out.println("some other error occurred");
			}
		}


	}

	@Override
	public void changePassword(Person p, String newPswd, String apikey) throws IOException, ValidationException, ApiException {
		p.setPassword(newPswd);
		ResponseEntity<? extends Object> re=null;
		Person person=null;

		try {
			UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/"+p.getPersonId());
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
			requestHeaders.add("api-key", apikey);
			HttpEntity<Person> requestEntity = new HttpEntity<>(p,requestHeaders);
			ResponseEntity<Person> g = rt.exchange(uriBuilder.toUriString(), HttpMethod.PATCH,requestEntity, Person.class);



		} catch (HttpStatusCodeException e) {

			System.out.println(e.getStatusCode());



			 if((HttpStatus.BAD_REQUEST == e.getStatusCode()))  {
				String serr = e.getResponseBodyAsString();
				System.out.println(serr);
				ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
				System.out.println(ae.getDescription());
				throw new ValidationException("pass not long enough");
			}

			if((HttpStatus.GONE == e.getStatusCode()))  {
				String serr = e.getResponseBodyAsString();
				System.out.println(serr);
				ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
				System.out.println(ae.getDescription());
				throw new ApiException("api is incorrect");
			}
		}



	}

	@Override
	public List<Person> findPersonsByCompanyName(String name) {

		UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/company").queryParam("name", name);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", MediaType.APPLICATION_XML_VALUE);
		HttpEntity<Persons> requestEntity = new HttpEntity<>(createHeaders("user1","pwd1"));
		ResponseEntity<Persons> g = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET,requestEntity, Persons.class);

		Persons p= g.getBody();
		return p.getPersons();
	}

}
