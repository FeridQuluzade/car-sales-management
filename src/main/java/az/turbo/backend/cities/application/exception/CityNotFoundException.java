package az.turbo.backend.cities.application.exception;

public class CityNotFoundException extends  RuntimeException {
    public CityNotFoundException(String message){
        super(message);
    }
}
