package be.abis.cllientsandwich.exception;

public class SandwichTypeNotFoundException extends Exception{

    public SandwichTypeNotFoundException() {
        super("Sandwich type not found.");
    }

    public SandwichTypeNotFoundException(String message) {
        super(message);
    }

}
