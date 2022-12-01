package be.abis.exjunit.service;


import be.abis.exjunit.exception.NoAbisEmplyException;
import be.abis.exjunit.exception.SalaryTooLowException;
import be.abis.exjunit.model.Person;

public interface PaymentService {
    void paySalary(Person person) throws SalaryTooLowException, NoAbisEmplyException;
}
