package az.turbo.backend.supplies.application.exception;

public class SupplyNotFoundException extends RuntimeException{
    public SupplyNotFoundException(String message){
        super(message);
    }
}
