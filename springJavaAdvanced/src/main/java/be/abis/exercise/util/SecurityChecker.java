package be.abis.exercise.util;

import be.abis.exercise.model.Person;

public class SecurityChecker {

    public boolean isAbisEmployee(Person person){
         return person.getCompany().getName().toLowerCase().equals("abis");
    }
}
