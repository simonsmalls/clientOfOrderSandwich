package be.abis.exercise.service;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import org.springframework.stereotype.Service;


public interface AddressService {
    void checkZipCode(String countryCode,String zipCode) throws ZipCodeNotCorrectException;
}
