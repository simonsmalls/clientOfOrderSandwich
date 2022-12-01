package be.abis.cllientsandwich.exception;

public class PersonNotFoundException extends Exception{

    public PersonNotFoundException() {
        super("Person not found.");
    }

    public PersonNotFoundException(String message) {
        super(message);
    }


}
