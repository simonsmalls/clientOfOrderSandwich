package be.abis.cllientsandwich.exception;

public class ShopAlreadyExistsException extends Exception{

    public ShopAlreadyExistsException() {
        super("Shop already exists.");
    }

    public ShopAlreadyExistsException(String message) {
        super(message);
    }

}
