package be.abis.exercise.model;

import be.abis.exercise.exception.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Objects;

public class Person implements Instructor, CourseParticipant, Comparable<CourseParticipant> {

	private static int counter = 0;
	Logger l2 = LogManager.getLogger("Console");


	private int personNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;
	private Company company;
	private int age;
	String regex= "\\S+@[a-z]+\\.[a-z]{2,5}";

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		personNumber = ++counter;
	}

	public Person(String firstName, String lastName, Company company) {
		this(firstName, lastName);
		this.company = company;
	}
	
	public Person(String firstName, String lastName, LocalDate birthDate, String email,
			String password, Company company) throws EmailException {
		this(firstName,lastName,company);
		try {
			checkEmail(email);
			this.email = email;
			this.password = password;
		} catch (EmailException e) {
			System.out.println(e.getMessage() + " "+this.firstName);
		}
		this.birthDate = birthDate;

	}

	public Person(String firstName, String lastName, LocalDate birthDate, String email,
			String password)  {
		this(firstName,lastName);
		try {
			checkEmail(email);
			this.email = email;
			this.password = password;
		} catch (EmailException e) {
			System.out.println(e.getMessage() + " "+this.firstName);
		}
		this.birthDate = birthDate;

	}

	public void checkEmail(String email) throws EmailException {

		if (!email.matches(regex)) throw new EmailException("email not correct");


	}

	public int getAge() {
		return calculateAge();
	}

	public int getPersonNumber() {
		return personNumber;
	}
	
	public void setPersonNumber(int personNumber) {
		this.personNumber=personNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		lastName = lName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws EmailException {

			checkEmail(email);
			this.email = email;



	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static int getNumberOfPersons() {
		return counter;
	}
/*
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
	*/


	public void teach(Course course) {
		System.out.println(this + " teaches " + course.getTitle());
	}

	public void attendCourse(Course course) {
		System.out.println(this + " follows " + course.getTitle());
	}
	
	@Override
	public int compareTo(CourseParticipant o) {
		return this.lastName.compareTo(((Person)o).lastName);
	}

    public static class FirstNameComparator implements Comparator<CourseParticipant>{
		@Override
		public int compare(CourseParticipant o1, CourseParticipant o2) {
			return ((Person)o1).getFirstName().compareToIgnoreCase(((Person)o2).getFirstName());
		}
    	 
    }
	public int calculateAge()  {
		LocalDate now =LocalDate.now();
		Period diff= Period.between(birthDate,now);
		age= diff.getYears();


		return age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return firstName.equals(person.firstName) && lastName.equals(person.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}
	public String toString(){
		return firstName+" "+lastName;
	}

}