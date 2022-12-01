package be.abis.exjunit.service;


import be.abis.exjunit.exception.NoAbisEmplyException;
import be.abis.exjunit.exception.SalaryTooLowException;
import be.abis.exjunit.model.Person;
import be.abis.exjunit.util.SecurityChecker;

public class AbisPaymentService implements PaymentService{

    SecurityChecker securityChecker;
    @Override
    public void paySalary(Person person) throws SalaryTooLowException, NoAbisEmplyException {

        if(!getSecurityChecker().isAbisEmployee(person)) throw new NoAbisEmplyException("not an abis employee");
        System.out.println(person.getFirstName()+" gets paid "+ person.calculateNet());
    }

    public SecurityChecker getSecurityChecker() {
        return securityChecker;
    }

    public void setSecurityChecker(SecurityChecker securityChecker) {
        this.securityChecker = securityChecker;
    }
}
