package az.turbo.backend.colors.application.exception;

public class ColorNotFoundException extends RuntimeException{
    public ColorNotFoundException(String message){
        super(message);
    }
}
