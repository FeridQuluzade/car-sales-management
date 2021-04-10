package az.turbo.backend.cities.application.exception;

public class CityNotFoundException extends  Exception {
    public CityNotFoundException(String message){
        super(message);
    }
}
