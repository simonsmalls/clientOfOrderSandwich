package be.abis.exercise.model;

import be.abis.exercise.exception.InvoiceException;

import java.time.LocalDate;


public class CompanySession extends Session {
	private static final double MIN_INVOICE = 900;
	private static final double MAX_INVOICE = 5000;
	
	private Company organizer;
	private int numberOfPersons;

	public CompanySession(CourseInterface course, LocalDate date, Company location,
			Instructor instructor, Company organizer) {
		super( course, date, location, instructor);
		this.organizer=organizer;
	}
	
	public CompanySession(CourseInterface course, LocalDate date, Company location,
			Instructor instructor, Company organizer, int numberOfPersons) {
		this(course, date, location, instructor,organizer);
		this.numberOfPersons=numberOfPersons;
	}	

	@Override
	public Company getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Company organizer) {
		this.organizer = organizer;
	}
	
	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	@Override
	public String toString() {
		String text= super.toString();
		text += " Organized by " + this.getOrganizer().getName();
		return text;
	}

	@Override
	public double invoice() throws InvoiceException {
		CourseInterface c = this.getCourse();
		double total = c.getDailyPrice() * c.getDays() * getNumberOfPersons();
		if (total > MAX_INVOICE) {
			throw new InvoiceException("Invoice exceeds limit");
		}
		if (total < MIN_INVOICE) {
		    throw new InvoiceException("Invoice is too low.");
		}
		return total;
	}



}