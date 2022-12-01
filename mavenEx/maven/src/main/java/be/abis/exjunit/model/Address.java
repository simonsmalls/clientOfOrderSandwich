package be.abis.exjunit.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Address {
	
	private String street;
	private String nr;
	private String zipCode;
	private String town;
	private String country;
	private String countryCode;
	
	public Address(String street, String nr, String zipCode, String town, String country, String countryCode) {
		this.street = street;
		this.nr = nr;
		this.zipCode = zipCode;
		this.town = town;
		this.country = country;
		this.countryCode = countryCode;
	}

	public boolean isBelgianZipCodeNumeric(){
		String regex="\\d{4}";
		return zipCode.matches(regex);
	}
	public void writeToFile() throws IOException {
		PrintWriter pw=new PrintWriter (new BufferedWriter(new FileWriter("c:\\temp\\javacourses\\address.csv",true)));
		pw.append(street);
		pw.append(";");
		pw.append(nr);
		pw.append(";");
		pw.append(zipCode);
		pw.append(";");
		pw.append(town);
		pw.append(";");
		pw.append(country);
		pw.append(";");
		pw.append(countryCode);
		pw.append(";");
		pw.append("\n");
		pw.close();

	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


}
