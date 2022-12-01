package be.abis.clientrest.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;
public class Person {

	private int personId;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate birthdate;
	private String emailAddress;
	private String password;
	private String language;
	private Company company;

	public Person(){}

	public Person(int personId, String firstName, String lastName,LocalDate birthdate, String emailAddress, String password, String language, Company company) {
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.emailAddress = emailAddress;
		this.password = password;
		this.language = language;
		this.company = company;
	}

	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Person with id " + personId + ", " + firstName + " "+ lastName + ", works for " +company.getName() + " in " + company.getAddress().getTown();
	}

	

}
