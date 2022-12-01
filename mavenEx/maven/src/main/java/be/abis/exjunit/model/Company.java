package be.abis.exjunit.model;

public class Company {
	
	private String name;
	private Address address;
	
	public Company(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public double calculateTaxToPay(){
		if(address.getCountryCode()=="BE"){
			return 51.0;
		}else if( address.getCountryCode()=="NL"){
			return 47.0;
		}else {return 35.0;}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	

}
