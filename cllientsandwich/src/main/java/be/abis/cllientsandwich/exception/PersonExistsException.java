package be.abis.cllientsandwich.exception;

public class PersonExistsException extends Exception{

    public PersonExistsException() {
        super("Person not found.");
    }

    public PersonExistsException(String message) {
        super(message);
    }


}
