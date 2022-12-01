package be.abis.exercise.service;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import org.springframework.stereotype.Service;

@Service
public class AddressIntImpl implements AddressService{
    @Override
    public void checkZipCode(String countryCode,String zipCode) throws ZipCodeNotCorrectException {
        String regexp2 = "[1-9]\\d{3}";
        String regexp3 = "[1-9]\\d{3}[A-Z]{2}";

        if (countryCode.equals("BE")){
            if((zipCode== null || !zipCode.matches(regexp2)) )throw new ZipCodeNotCorrectException("zipcode incorrect for BE");
        }
        if (countryCode.equals("NL")){
            if((zipCode== null || !zipCode.matches(regexp3))) throw new ZipCodeNotCorrectException("zipcode incorrect for NL");
        }
    }
}
