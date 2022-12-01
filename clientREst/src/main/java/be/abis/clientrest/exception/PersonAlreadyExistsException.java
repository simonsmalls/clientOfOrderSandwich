package be.abis.clientrest.exception;

public class PersonAlreadyExistsException extends Exception{
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
