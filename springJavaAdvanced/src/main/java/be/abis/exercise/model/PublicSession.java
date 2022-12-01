package be.abis.exercise.model;


import be.abis.exercise.exception.InvoiceException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class PublicSession extends Session {
	
	public final static Company ABIS = new Company("Abis");
	private ArrayList<CourseParticipant> enrolments = new ArrayList<CourseParticipant>();

	public PublicSession(Course course, LocalDate date, Company location,
			Instructor instructor) {
		super(course, date, location, instructor);
	}

	@Override
	public Company getOrganizer() {
		return ABIS;
	}

	public ArrayList<CourseParticipant> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(ArrayList<CourseParticipant> enrolments) {
		this.enrolments = enrolments;
	}

	@Override
	public double invoice() throws InvoiceException {
		System.out.println("Invoice in PublicSession");
		return 500;
	}

	public double revenue(){
		BigDecimal bd1=new BigDecimal(getCourse().getDailyPrice());
		BigDecimal bd2=new BigDecimal(10);
		BigDecimal bd3=new BigDecimal(getCourse().getDays());

		BigDecimal bd4=new BigDecimal("0.79");
		BigDecimal bd5=new BigDecimal(enrolments.size());
		Locale bel=new Locale("nl","BE");


		BigDecimal bd6=bd1.multiply(bd2).multiply(bd3).multiply(bd4).multiply(bd5);
		//formatting(bd5.doubleValue());

		return bd6.doubleValue();
	}

	public void formatting(double d){
		String NNBSP =  "\u00A0";

		Locale bel=new Locale("nl","BE");

		NumberFormat df2 = DecimalFormat.getCurrencyInstance(bel);
		df2.setGroupingUsed(false);




		String output=df2.format(d);
		System.out.println(output.replace(NNBSP,""));

	}

	public void addEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			this.addEnrolment(c);
	}

	protected void addEnrolment(CourseParticipant cp) {
		if (!enrolments.contains(cp)) {
			enrolments.add(cp);
			System.out.println("Enrolment added to the list, now "
					+ enrolments.size() + " enrolments.");
			System.out.println("enrollee is: " + cp);
		} else {
			System.out.println("Couldn't add " + cp + " as enrollee, since he was already enrolled");
		}
	}

	public void removeEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			removeEnrolment(c);
	}

	protected void removeEnrolment(CourseParticipant cp) {
		if (enrolments.contains(cp)) {
			enrolments.remove(cp);
			System.out.println("Enrollee " + cp + " removed from the list, now "
					+ enrolments.size() + " enrolments.");
		} else {
			System.out.println("Couldn't remove enrolment.");
		}
	}

	public void printListOfParticipants(){
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			//do this one tuesday
			String title="UNIX/Linux shell programming";
			String split="-----------------------------------------------------------------------------------";
			 fw = new FileWriter("c:\\temp\\javacourses\\participants.txt", false);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			StringBuilder sb2 = new StringBuilder();
			String instructorString="instructor:";
			String locationString="location:";
			sb2.append(instructorString+((Person)getInstructor()).getFirstName()+" "+((Person)getInstructor()).getLastName());
			pw.printf("%1$-25S", instructorString);
			pw.append(((Person)getInstructor()).getFirstName()+" "+((Person)getInstructor()).getLastName()+"\n");
			StringBuilder sb4 = new StringBuilder();
			pw.printf("%1$-25S", locationString);
			pw.append(getLocation().getName()+", "+getLocation().getAddress().getStreet()+" "+getLocation().getAddress().getNr()
					+", "+getLocation().getAddress().getZipCode()+" "+getLocation().getAddress().getTown()+"\n");

			for(CourseParticipant c:enrolments) {

				if (((Person) c).getCompany() == null) {
					((Person) c).setCompany(new Company("unknown"));

				}
			}

			pw.println(split);
			int index=0;
			//Collections.sort(enrolments, Comparator.comparing(Person::getFirstName));
			enrolments.sort(Comparator.comparing(e -> ((Person) e).getFirstName()));
			enrolments.sort((e1,e2)-> ((Person)e1).getCompany().getName().compareTo(((Person)e2).getCompany().getName()));


			for(CourseParticipant c:enrolments){
				index++;
				if(((Person)c).getCompany()==null){
					if(index<10) {
						pw.printf("%1$-10S", "0"+index);
						pw.printf("%1$-15S", "not known");
						pw.println(((Person) c).getFirstName() + " " + ((Person) c).getLastName());
					}else{
						pw.printf("%1$-10S", index);
						pw.printf("%1$-15S", "not known");
						pw.println(((Person) c).getFirstName() + " " + ((Person) c).getLastName());
					}
				}else{
					pw.printf("%1$-10S%2$-15S%3$s", "0"+index,((Person)c).getCompany().getName(),((Person)c).getFirstName()+" "+((Person)c).getLastName()+"\n");
					//pw.printf("%1$-15s",((Person)c).getCompany().getName());
					//pw.println(((Person)c).getFirstName()+" "+((Person)c).getLastName());
				}



			}
			pw.println(split);


		}catch (IOException e) {
				throw new RuntimeException(e);
		} finally {
				try {
					pw.close();
					bw.close();
					fw.close();
				}
				catch (IOException io) {}

		}

	}
	
	public Iterator<CourseParticipant> getEnrolmentsIterator(){
		return enrolments.iterator();
	}

	public void toStringInt(Locale locale){

		//String messageKey = "login.firstname";
		String baseName = "properties.applicationResource";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
		//String messageDefaultLoc = bundle.getString(messageKey);
		//ResourceBundle bundle2 = ResourceBundle.getBundle(baseName, locale);
		//String messageLocFR = bundle2.getString(messageKey);
		String message = MessageFormat.format(bundle.getString("message"), "Abis");
		String message2 = (bundle.getString("company"));
		System.out.println(message);
		System.out.println(message2);

	}
	
	

	
}