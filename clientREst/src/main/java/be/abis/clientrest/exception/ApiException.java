package be.abis.clientrest.exception;

public class ApiException extends Exception{
    public ApiException(String message) {
        super(message);
    }
}
